package com.siliconvalleyoffice.git4jira.model

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.concurrent.Task
import tornadofx.*
import java.net.MalformedURLException
import java.util.stream.Collectors
import javax.json.Json
import javax.json.JsonValue


class JsonRetrievalTask<T> protected constructor(private val objectFactory: ObjectFactory<T>, endpoint: String) : Task<Collection<T>>() {
    override fun call(): Collection<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @FunctionalInterface
    interface ObjectFactory<T> {

        fun createObject(jsonValue: JsonValue): T
    }

//    init {
//        var hlp: URL? = null
//        try {
//            hlp = URL(String.format("%s%s", BASE_URL, endpoint))
//        } catch (e: MalformedURLException) {
//            // I hope so ;)
//            throw RuntimeException(e)
//        }
//
//        this.apiEndpoint = hlp
//    }
//
//    @Throws(Exception::class)
//    protected fun call(): Collection<T> {
//        logger.log(Level.FINE, "Retrieving list of objects from {0}", arrayOf<Any>(this.apiEndpoint.toString()))
//        Json.createReader(apiEndpoint.openStream()).use({ jsonReader ->
//            logger.log(Level.FINE, "Done.")
//            return jsonReader.readArray().stream().map(Function<JsonValue, T> { objectFactory.createObject(it) }).collect(Collectors.toList<T>())
//        })
//    }
//
//    companion object {
//
//        val HOST_AND_PORT = "http://testserver.com"
//        val BASE_URL = "$HOST_AND_PORT/api"
//        private val logger = Logger.getLogger(JsonRetrievalTask<*>::class.java.name)
//
//
//        operator fun <T> get(objectFactory: ObjectFactory<T>, endpoint: String): ObservableList<T> {
//            val rv = FXCollections.observableArrayList<T>()
//
//            val bikesRetrievalTask = JsonRetrievalTask(objectFactory, endpoint)
//            bikesRetrievalTask.setOnSucceeded { state -> rv.addAll(state.getSource().getValue() as Collection<T>) }
//            Thread(bikesRetrievalTask).start()
//            return rv
//        }
//    }
}