package kr.pe.junho85.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class CustomerController {
    @Autowired
    lateinit var customerService: CustomerService

    @GetMapping("/customers/{id}")
    fun getCustomer(@PathVariable id : Int) = customerService.getCustomer(id)

    @PostMapping("/customers")
    fun createCustomer(@RequestBody customer: Customer) {
        customerService.createCustomer(customer)
    }

    @DeleteMapping("/customers/{id}")
    fun deleteCustomer(@PathVariable id: Int) = customerService.deleteCustomer(id)

    @PutMapping("/customers/{id}")
    fun updateCusomter(@PathVariable id: Int, @RequestBody customer: Customer) {
        customerService.updateCustomer(id, customer)
    }

    @GetMapping("/customers")
    fun getCustomers(@RequestParam(required = false, defaultValue = "")nameFilter: String) =
            customerService.searchCustomers(nameFilter)

//    @GetMapping("/customers")
//    fun getCustomers(@RequestParam(required = false, defaultValue = "")
//    nameFilter: String) = customers.filter {
//        it.value.name.contains(nameFilter, true)
//    }.map(Map.Entry<Int, Customer>::value).toList()

}