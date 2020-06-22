/**
 * Copyright (C) 2018 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sawah.signalrtest.common.infrastructure.exception

import com.sawah.signalrtest.common.infrastructure.exception.Failure.FeatureIpvError
import com.sawah.signalrtest.common.infrastructure.extensions.empty
import com.sawah.signalrtest.data.Message


/**
 * Base Class for handling errors/FailureErrors/exceptions.
 * Every feature specific Failure should extend [FeatureIpvError] class.
 */
@Suppress("unused")
sealed class Failure {

    var message: Message = Message()
    var errorMessage: String = String.empty()

    object NetworkConnection : Failure()
    object ServerError : Failure()
    object AuthError : Failure()
    object BadRequest : Failure()
    object NotFound : Failure()
    object NoDataFound : Failure()
    object UnSupportedMediaType : Failure()
    object MalFormedJson : Failure()
    object IllegalStateException : Failure()
    object JsonSyntaxException : Failure()
    object InternalServerError : Failure()
    object AndroidError : Failure()
    object UniqueConstraintError : Failure()
    object UserNotFound : Failure()
    object FacebookLoginError : Failure()

    /** * Extend this class for feature specific AbsherErrors.*/
    abstract class FeatureIpvError : Failure()
}
