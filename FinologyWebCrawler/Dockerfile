FROM openjdk:8
ADD target/finology-web-crawler.jar finology-web-crawler.
ADD finology.db finology.db
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "finology-web-crawler.jar"]

