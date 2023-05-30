package dz.nexatech.reporter.client.common

import com.google.common.collect.ImmutableList
import kotlin.reflect.KClass
import kotlin.reflect.cast

inline fun <T, reified R> Iterable<T>.mapToArray(
    nullValue: R,
    arraySize: Int = if (this is Collection) this.size else 1,
    mapper: (T) -> R,
): Array<R> {
    val result: Array<R> = Array(arraySize) { nullValue }
    var i = 0
    val iterator = iterator()
    while (i < arraySize && iterator.hasNext()) {
        result[i] = mapper(iterator.next())
        i++
    }

    return result
}

fun <T> Iterator<T>.indexDiff(filter1: (T) -> Boolean, filter2: (T) -> Boolean): Int? {
    var index1 = -1
    var index2 = -1
    var i = -1
    for (value in this) {
        i++
        if (index1 == -1 && filter1.invoke(value)) {
            index1 = i
        }
        if (index2 == -1 && filter2.invoke(value)) {
            index2 = i
        }
        if (index1 > -1 && index2 > -1) {
            return index2 - index1
        }
    }

    return null
}

inline fun <T> MutableIterator<T>.removeIf(
    preRemove: (T) -> Unit = {},
    postRemove: (T) -> Unit = {},
    filter: (T) -> Boolean
) {
    while (hasNext()) {
        val next = next()
        if (filter(next)) {
            preRemove(next)
            remove()
            postRemove(next)
        }
    }
}

fun <E : Any, T : E> List<E>?.filterByClass(type: KClass<T>): List<T>? {
    if (isNullOrEmpty()) {
        return null
    } else {
        val result = ArrayList<T>(this.size)
        for (e: E in this) {
            if (type.isInstance(e)) {
                result.add(type.cast(e))
            }
        }
        return if (result.isEmpty()) null else result
    }
}

fun <T: Any> Iterable<T>.slice(count: Int = 2): ImmutableList<ImmutableList<T>> {
    val result = ImmutableList.builder<ImmutableList<T>>()
    if (count <= 1) {
        for (value in this) {
            result.add(ImmutableList.of(value))
        }
    } else {
        val iterator = iterator()
        while (iterator.hasNext()) {
            var i = 0
            val sliceBuilder = ImmutableList.builder<T>()
            while (i < count && iterator.hasNext()) {
                sliceBuilder.add(iterator.next())
                i++
            }
            result.add(sliceBuilder.build())
        }
    }

    return result.build()
}