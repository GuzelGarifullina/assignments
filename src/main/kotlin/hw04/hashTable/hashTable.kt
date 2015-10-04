package hw04.hashTable

import hw04.AbstractSet
import java.util.*

/* Realization of abstract set interface
   for AVL trees
   made by Guzel Garifullina
   Estimated time 4 hours
   real time      3 hour
*/

public class hashTable<T : Comparable<T>>() : AbstractSet<T> {
    private  val size = 100
    private var place = size
    private fun nextHash (hash : Int) : Int {
        return (hash + 1).hashCode() % size
    }
    private  fun returnEmpty() :  ArrayList<T?>{
        var arr = ArrayList<T?>()
        for (i in 0.. (size - 1)){
            arr.add( null)
        }
        return arr
    }
    private  val empty  :  ArrayList<T?> = returnEmpty()
    private var table  :  ArrayList<T?> = empty
    override protected fun makeEmpty() {
        var arr = ArrayList<T?>()
        for (i in 0.. (size - 1)){
            arr.add( null)
        }
        table = arr
    }

    override public fun insert (value: T) {
        fun insertf(hashCode : Int, value: T) {
            if (table.get(hashCode) == null ) {
                table.set(hashCode, value)
            }
            else if (table.get(hashCode) != value) {
                insertf(nextHash(hashCode), value)
            }
        }
        if (place == 0) {
            if (! this.search(value)){
                throw  Exception("There no free place")
            }
            else  {
                return
            }
        }
        insertf(value.hashCode() % size, value)
    }
    override public fun delete (value : T) : Boolean{
        fun del (hashCode:Int, value: T, iter: Int) : Boolean{
            if (iter > size){
                return false
            }
            if (table.get(hashCode) != value) {
                return del (nextHash(hashCode), value, iter + 1)
            }
            else {
                table.set(hashCode, null)
                return true
            }
        }
        return del (value.hashCode() % 100, value, 0)
    }
    override public fun search (value : T) : Boolean{
        fun find (hashCode:Int, value: T, iter: Int) : Boolean{
            if (iter > size){
                return false
            }
            if (table.get(hashCode) == null ) {
                return false
            }
            if (table.get(hashCode) != value) {
                return find (nextHash(hashCode), value, iter + 1)
            }
            else {
                return true
            }
        }
        return find (value.hashCode() % 100, value, 0)
    }
    override public fun toList(): ArrayList<T> {
        var list = ArrayList<T>()
        for (element in table){
            if (element != null){
                list.add(element)
            }
        }
        return list
    }
    override public fun union (set : AbstractSet<T>) : AbstractSet<T> {
        val list = set.toList()
        val set = this
        for (value in list){
            set.insert(value)
        }
        return set
    }
    override public fun intersection (set : AbstractSet<T>) : AbstractSet<T> {
        fun getResultedList(set : AbstractSet<T>) : List<T> {
            val list = this.toList()
            val list2 = set.toList()
            var resultedList = ArrayList<T>()
            for (elem in list2){
                if (list.contains (elem)){
                    resultedList.add(elem)
                }
            }
            return  resultedList
        }
        val resultedList = getResultedList(set)
        val set = this
        set.makeEmpty()
        for (elem in resultedList){
            set.insert(elem)
        }
        return set
    }
}

fun main(args: Array<String>) {
    val set = hashTable<Int>()
    set.insert(1)
    set.insert(2)
    set.insert(7)
    set.insert(0)
    val list = set.toList()
    println(list)

    val set2 = hashTable<Char>()
    set2.insert('1')
    set2.insert('2')
    set2.insert('7')
    set2.insert('0')
    val list2 = set2.toList()
    println(list2)
}