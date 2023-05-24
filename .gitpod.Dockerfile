FROM gitpod/workspace-full

USER gitpod

RUN bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh && \
    sdk install java 19.0.2-librca && \
    sdk install java 22.3.r19-grl && \
    sdk install gradle 7.6.1 && \
    sdk default gradle 7.6.1 && \
    sdk default java 19.0.2-librca"
