package kr.pe.junho85.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ConcurrentHashMap

@RestController
class CustomerController {
    @Autowired
    lateinit var customers : ConcurrentHashMap<Int, Customer>

    @RequestMapping(value = ["/customer"], method = [RequestMethod.GET])
    fun getCustomer() = customers[2]
//            Customer(1, "Kotlin")
}