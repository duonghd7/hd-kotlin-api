package com.hdd.kotlin_caf.scope

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * Created on 2/28/2018.
 * @author duonghd
 */

@Suppress("DEPRECATED_JAVA_ANNOTATION")
@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class ApplicationScope