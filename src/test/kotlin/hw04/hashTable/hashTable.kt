package hw04.hashTable

/* Tests for realization of abstract
   set interface for AVL trees
   made by Guzel Garifullina
   Estimated time 30 minutes
   real time      1 hour
*/

import org.junit.Test
import java.util.*
import kotlin.test.assertFalse
import kotlin.test.assertTrue

public class hashTab {
    fun compareLists<T : Comparable<T>>(list1: ArrayList<T>, list2 : ArrayList<T>): Boolean {
        if (list1.lastIndex != list2.lastIndex){
            return false
        }
        var i2 = 0
        for (elem in list1){
            if (elem != list2.get(i2)){
                return false
            }
            i2++
        }
        return  true
    }
    var set : hashTable<Int> = hashTable()
    @Test fun Find1(){
        assertFalse (set.search(1))
    }
    @Test fun FindAndAdd(){
        set.insert(1)
        assertTrue (set.search(1))
    }
    @Test fun FindAndAdd2(){
        set.insert(1)
        set.insert(2)
        assertTrue (set.search(1))
    }
    @Test fun FindAddRemove(){
        set.insert(1)
        set.delete(1)
        assertFalse (set.search(1))
    }
    @Test fun AddManyTimes(){
        set.insert(1)
        set.insert(1)
        set.insert(1)
        set.insert(1)
        set.insert(1)
        assertTrue (set.search(1))
    }
    @Test fun AddManyTimesAndDelete(){
        set.insert(1)
        set.insert(1)
        set.insert(1)
        set.insert(1)
        set.delete(1)
        assertFalse (set.search(1))
    }
    @Test fun ToList(){
        set.insert(1)
        set.insert(2)
        set.insert(7)
        set.insert(0)
        val expectedList = ArrayList<Int>(
                Arrays.asList(0, 1, 2, 7))
        assertTrue (compareLists(expectedList, set.toList()))
    }
    @Test fun Remove(){
        set.insert(1)
        set.insert(2)
        set.insert(7)
        set.insert(0)
        set.delete(2)
        val expectedList = ArrayList<Int>(
                Arrays.asList(0, 1, 7))
        assertTrue (compareLists(expectedList, set.toList()))
    }
    @Test fun Remove2(){
        set.insert(1)
        set.insert(2)
        set.insert(7)
        set.insert(0)
        set.delete(2)
        set.delete(0)
        val expectedList = ArrayList<Int>(
                Arrays.asList(1, 7))
        assertTrue (compareLists(expectedList, set.toList()))
    }
    var set2 = hashTable<Int>()
    @Test fun Union(){
        set.insert(1)
        set.insert(2)
        set.insert(7)
        set.insert(0)

        set2.insert(8)
        set2.insert(0)
        set2.insert(6)
        set2.insert(7)
        val expectedList = ArrayList<Int>(
                Arrays.asList(0, 1, 2, 6, 7, 8))
        assertTrue (compareLists(expectedList, set.union(set2).toList()))
    }
    @Test fun Intersection(){
        set.insert(1)
        set.insert(2)
        set.insert(7)
        set.insert(0)

        set2.insert(8)
        set2.insert(0)
        set2.insert(6)
        set2.insert(7)
        val expectedList = ArrayList<Int>(
                Arrays.asList(0, 7))
        assertTrue (compareLists(expectedList, set.intersection(set2).toList()))
    }
    val setC = hashTable<Char>()
    @Test fun TestChar(){
        setC.insert('1')
        setC.insert('2')
        setC.insert('7')
        setC.insert('0')
        setC.delete('2')
        val expectedList = ArrayList<Char>(
                Arrays.asList('0', '1', '7'))
        assertTrue (compareLists(expectedList, setC.toList()))
    }
}
