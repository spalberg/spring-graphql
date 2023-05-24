FROM gitpod/workspace-full

USER gitpod

RUN bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh && \
    sdk install java 19.0.2-librca && \
    sdk install gradle 8.1.1 && \
    sdk install java 22.3.r19-grl && \
    sdk default java 19.0.2-librca"
