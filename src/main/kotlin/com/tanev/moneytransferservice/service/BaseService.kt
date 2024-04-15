package com.tanev.moneytransferservice.service

import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired

open class BaseService {

    @Autowired
    protected lateinit var db: DSLContext
}