package mobi.foo.dokkapracticing.domain.models.main

import java.io.Serializable

data class CompanyModel(
    var founderName: String = "Elon Musk",
    var foundedYear: String = "2002",
    var numberOfEmployees: Int = 7000,
    var numberOfLaunchSites: Int = 3,
    var companyValue: Long = 2750000000L
) : Serializable
