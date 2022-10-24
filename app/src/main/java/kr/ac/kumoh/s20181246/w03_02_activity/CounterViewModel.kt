package kr.ac.kumoh.s20181246.w03_02_activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {
    private var _count = 0
    var count
        get() = _count
        set(value) {
            _count = value
        }
    fun add() {
        _count++
    }
}

//class CounterViewModel : ViewModel() {
//    private val _count = MutableLiveData<Int>(0)
//
//    fun getCount() = _count
//    fun add() {
//        _count.value = _count.value?.plus(1)
//    }
//    fun minus() {
//        _count.value = _count.value?.minus(1)
//    }
//}