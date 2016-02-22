## Spring Boot Playground
A playground for stuff I had to hack out in spring using kewl spring boot as the base of it all.

- *the curious case of hiding entity fields in spring data rest response*

>    - This is done via using Jackson modules to customize data rest behaviour which can be easily hacked by using a serializerModifier as in class [FilteringSerializerModifier](src/main/me/gauravbrills/FilteringSerializerModifier.java)
- A small hook has been added to demonstrate a use case where the spring security roles are used to get fields to be hidden per user. The repository for ExcludedField Holds this mapping and the logic resides in [RoleBasedFilterServiceImpl](src/main/me/gauravbrills/RoleBasedFilterServiceImpl.java)

-  


more to come ...


