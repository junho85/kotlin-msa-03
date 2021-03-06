package kr.pe.junho85.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CustomerController {
    @Autowired
    lateinit var customerService: CustomerService

    @GetMapping("/customers/{id}")
    fun getCustomer(@PathVariable id : Int): ResponseEntity<Any> {
//    fun getCustomer(@PathVariable id : Int): ResponseEntity<Customer> {
        val customer = customerService.getCustomer(id)
        return if (customer != null)
            ResponseEntity(customer, HttpStatus.OK)
        else
            ResponseEntity(ErrorResponse("Customer Not Found", "customer '$id' not found"), HttpStatus.NOT_FOUND)

//        val customer = customerService.getCustomer(id) ?:
//                throw CustomerNotFoundException("customer '$id' not found")
//        return ResponseEntity(customer, HttpStatus.OK)

//        val status = if (customer == null) HttpStatus.NOT_FOUND else HttpStatus.OK
//        return ResponseEntity(customer, status)

    }

    @PostMapping("/customers")
    fun createCustomer(@RequestBody customer: Customer): ResponseEntity<Unit?> {
        customerService.createCustomer(customer)
        return ResponseEntity(Unit, HttpStatus.CREATED)
    }

    @DeleteMapping("/customers/{id}")
    fun deleteCustomer(@PathVariable id: Int): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if (customerService.getCustomer(id) != null) {
            customerService.deleteCustomer(id)
            status = HttpStatus.OK
        }
        return ResponseEntity(Unit, status)
    }

    @PutMapping("/customers/{id}")
    fun updateCusomter(@PathVariable id: Int, @RequestBody customer: Customer): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if (customerService.getCustomer(id) != null) {
            customerService.updateCustomer(id, customer)
            status = HttpStatus.ACCEPTED
        }
        return ResponseEntity(Unit, status)
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