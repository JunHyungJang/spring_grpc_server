package com.example.grpcserver;

import io.grpc.ServerBuilder;
import io.grpc.Server;

import javax.imageio.IIOException;
import java.io.IOException;

public class GrpcServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(6565)
                .addService(new BankService())
                .build();
        server.start();

        server.awaitTermination();
    }
}
