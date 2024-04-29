package mobi.foo.dokkapracticing.domain.models.main

/

data class MainState(
    val launches: List<LaunchModel> = listOf(),
    val isLoading: Boolean = false,
    val companyModel: CompanyModel = CompanyModel()
)

/*
in MVI pattern we put all of our screen's state in a one single state wrapper class into ui state class,
we treat this class as a single source of truth, only contains immutable properties that cant be changed and if our state changes, then
the goal of the mvi is to replace the whole state with the new instance and the changed fields.
 */