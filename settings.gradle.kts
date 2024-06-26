rootProject.name = "labs"
include("banks-core")
include("banks-console")
include("Service")
include("Controller")
include("DAO")
include("api-gateway")
include("cats")
include("owners")
include("api-gateway:api-gateway-app")
findProject(":api-gateway:api-gateway-app")?.name = "api-gateway-app"
include("api-gateway:security")
findProject(":api-gateway:security")?.name = "security"
include("cats:cats-app")
findProject(":cats:cats-app")?.name = "cats-app"
include("cats:cats-client")
findProject(":cats:cats-client")?.name = "cats-client"
include("owners:owners-app")
findProject(":owners:owners-app")?.name = "owners-app"
include("owners:owners-client")
findProject(":owners:owners-client")?.name = "owners-client"
include("owners:owners-modules")
findProject(":owners:owners-modules")?.name = "owners-modules"
include("owners:owners-models")
findProject(":owners:owners-models")?.name = "owners-models"
include("cats:cats-models")
findProject(":cats:cats-models")?.name = "cats-models"
include("api-gateway:api-gateway-models")
findProject(":api-gateway:api-gateway-models")?.name = "api-gateway-models"
include("api-gateway:api-gateway-app:api-gateway-kafka")
findProject(":api-gateway:api-gateway-app:api-gateway-kafka")?.name = "api-gateway-kafka"
include("api-gateway:api-gateway-kafka")
findProject(":api-gateway:api-gateway-kafka")?.name = "api-gateway-kafka"
include("kafka-config")
