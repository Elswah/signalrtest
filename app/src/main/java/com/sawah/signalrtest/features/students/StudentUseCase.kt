package com.sawah.signalrtest.features.students


import com.sawah.signalrtest.common.infrastructure.arch.BaseUseCase
import com.sawah.signalrtest.common.infrastructure.exception.Failure
import com.sawah.signalrtest.common.infrastructure.functional.Either
import kotlinx.coroutines.CoroutineScope

class StudentUseCase(ioScope: CoroutineScope, private var studentRepository: StudentRepository) :
    BaseUseCase<String, String>(ioScope) {
    override suspend fun run(params: String): Either<Failure, String> =
        studentRepository.getRouteData()

}