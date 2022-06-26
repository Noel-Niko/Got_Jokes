package com.livingtechusa.gotjokes.domain.util

import com.livingtechusa.gotjokes.Data
import com.livingtechusa.gotjokes.Memes
import com.livingtechusa.gotjokes.domain.model.Image


interface DomainMapper <T, DomainModel>{
    fun mapToDomainModel(response: Data?): List<DomainModel>?
    fun mapFromDomainModel(domainModel: DomainModel): T
}