package com.taskforce.superinvention.config.test

import com.taskforce.superinvention.config.TestConfig
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@DataJpaTest
@Transactional
@ActiveProfiles(TestEnv.TEST)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig::class)
abstract class DataJpaRepoTest: BaseTest