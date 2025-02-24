package com.taskforce.superinvention.app.domain.club.album.image

import com.taskforce.superinvention.app.domain.club.album.ClubAlbum
import com.taskforce.superinvention.app.domain.common.image.webp.convert.WebpConvertService
import com.taskforce.superinvention.common.util.aws.s3.AwsS3Mo
import com.taskforce.superinvention.common.util.aws.s3.S3Path
import org.apache.commons.io.FilenameUtils
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class ClubAlbumImageService (
    private val webpConvertService: WebpConvertService,
    private val awsS3Mo: AwsS3Mo
) {

    @Transactional
    fun registerClubAlbumImage(clubAlbum: ClubAlbum, s3Path: S3Path): S3Path {
        val imgFolder = "clubAlbumImg/${clubAlbum.seq}"
        val movedFile = awsS3Mo.moveFile(s3Path, "$imgFolder/${s3Path.fileName}")
        webpConvertService.convertToWebP(movedFile)

        clubAlbum.img_url   = movedFile.filePath
        clubAlbum.file_name = movedFile.fileName
        return movedFile
    }

    @Transactional
    fun editClubAlbumImage(clubAlbum: ClubAlbum, s3Path: S3Path): S3Path {

        if(clubAlbum.img_url == s3Path.filePath) {
            return s3Path
        }

        val imgFolder = "clubAlbumImg/${clubAlbum.seq}"
        val movedFile = awsS3Mo.moveFile(s3Path, "$imgFolder/${s3Path.fileName}")
        webpConvertService.convertToWebP(movedFile)

        deleteAlbumImg(clubAlbum)

        clubAlbum.img_url   = movedFile.filePath
        clubAlbum.file_name = movedFile.fileName
        return movedFile
    }

    private fun deleteAlbumImg(clubAlbum: ClubAlbum) {
        val filePath      = clubAlbum.img_url
        val webpFilePath  = "${FilenameUtils.removeExtension(clubAlbum.img_url)}.webp"

        awsS3Mo.deleteFile(filePath)
        awsS3Mo.deleteFile(webpFilePath)
    }
}
