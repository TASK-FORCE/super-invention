package com.taskforce.superinvention.app.web.controller.club.album

import com.taskforce.superinvention.app.domain.club.album.like.ClubAlbumLikeService
import com.taskforce.superinvention.app.domain.user.User
import com.taskforce.superinvention.app.web.common.response.ResponseDto
import com.taskforce.superinvention.app.web.dto.club.album.like.ClubAlbumLikeDto
import com.taskforce.superinvention.common.config.argument.resolver.auth.AuthUser
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/club/{clubSeq}/album/{clubAlbumSeq}/like")
class ClubAlbumLikeController(
        private val clubAlbumLikeService: ClubAlbumLikeService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun registerClubAlbumLike(@AuthUser user: User,
                              @PathVariable clubSeq: Long,
                              @PathVariable clubAlbumSeq: Long): ResponseDto<ClubAlbumLikeDto> {

        return ResponseDto(data = clubAlbumLikeService.registerClubAlbumLike(user, clubSeq, clubAlbumSeq))
    }

    @DeleteMapping
    fun deleteClubAlbumLike(@AuthUser user: User,
                              @PathVariable clubSeq: Long,
                              @PathVariable clubAlbumSeq: Long): ResponseDto<ClubAlbumLikeDto> {

        return ResponseDto(data = clubAlbumLikeService.removeClubAlbumLike(user, clubSeq, clubAlbumSeq))
    }
}