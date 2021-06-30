package com.example.task1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*


@SpringBootApplication
class Task1Application

fun main(args: Array<String>) {
    runApplication<Task1Application>(*args)
}
@RestController
@RequestMapping
class WordResource(val service: WordService) {

    @GetMapping
    fun index(): List<Word> = service.findWords()

    @PostMapping
    fun post(@RequestBody message: Word) {
        service.post(message)
    }

    @ResponseBody
    @DeleteMapping
    fun delete(@RequestBody message: Word) {
        service.delete(message)
    }
}
@Service
class WordService(val db: WordRepository) {

    fun findWords(): List<Word> = db.findWords()

    fun post(message: Word){
        db.save(message)
    }
    fun delete(@RequestBody message: Word){
        db.delete(message)
    }
 }

interface WordRepository : CrudRepository<Word, String> {

    @Query("select * from messages")
    fun findWords(): List<Word>
}

@Table("MESSAGES")
data class Word (@Id val id: String?, val text: String)


