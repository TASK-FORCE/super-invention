package com.taskforce.superinvention.app.web

import com.taskforce.superinvention.app.domain.state.StateService
import com.taskforce.superinvention.app.web.dto.state.StateDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StateController(
        val stateService: StateService
) {

    @GetMapping("/states")
    fun getAllStateList(): List<StateDto> {
        val findByLevel = stateService.findAllStateDtoList()
        return findByLevel
    }

}