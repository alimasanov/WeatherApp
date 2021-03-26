package com.example.weatherapp.tools.extensions

import com.example.weatherapp.base.BaseView
import com.example.weatherapp.network.error.WebMessage
import com.google.gson.JsonObject
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

inline infix fun <reified T: Any> JsonObject.parseMaybe(key: String) : T? = try { parse(key) } catch (e: java.lang.Exception) { null }

inline infix fun <reified T: Any> JsonObject.parse(key: String) : T = when(T::class) {
    String::class -> getAsJsonPrimitive(key)?.asString ?: throw WebMessage.PARSE(key)
    Boolean::class -> getAsJsonPrimitive(key)?.asBoolean ?: throw WebMessage.PARSE(key)
    Int::class -> getAsJsonPrimitive(key)?.asInt ?: throw WebMessage.PARSE(key)
    Long::class -> getAsJsonPrimitive(key)?.asLong ?: throw WebMessage.PARSE(key)
    Double::class -> getAsJsonPrimitive(key)?.asDouble ?: throw WebMessage.PARSE(key)
    else -> throw WebMessage.UNKNOWN()
} as T

infix fun JsonObject.parseList(key: String) : List<JsonObject> =  getAsJsonArray(key).map { it.asJsonObject }

infix fun JsonObject.parseListMaybe(key: String): List<JsonObject>? = try { getAsJsonArray(key).map { it.asJsonObject } } catch (e: java.lang.Exception) { null }

fun <V: BaseView, U> Single<U>.viewStateProgress(viewState: V) = this
    .doOnSubscribe { viewState.startLoading() }
    .doFinally { viewState.completeLoading() }
    .doOnError { viewState.errorLoading(it) }

fun Disposable.addTo(disposables: CompositeDisposable) { disposables.add(this) }