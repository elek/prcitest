mvn -fn checkstyle:check
mvn checkstyle:checkstyle-aggregate
exit $?
