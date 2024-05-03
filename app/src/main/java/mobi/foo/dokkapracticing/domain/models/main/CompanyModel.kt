package mobi.foo.dokkapracticing.domain.models.main

import java.io.Serializable

/**
 * CompanyModel is a data class representing the details of a company.
 * It stores information such as founder's name, founded year, number of employees, number of launch sites, and company value.
 * This class implements Serializable to allow for serialization and deserialization.
 */
data class CompanyModel(
    var founderName: String = "Elon Musk",
    var foundedYear: String = "2002",
    var numberOfEmployees: Int = 7000,
    var numberOfLaunchSites: Int = 3,
    var companyValue: Long = 2750000000L
) : Serializable
