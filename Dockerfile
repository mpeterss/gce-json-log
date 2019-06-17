FROM azul/zulu-openjdk:11.0.1-11.2

LABEL maintainer="dev@redotter.sg"

COPY script/start.sh /start.sh

COPY build/libs/logging-uber.jar /log-test.jar

CMD ["/start.sh"]