package io.getarrays.server;

import io.getarrays.server.Model.Server;
import io.getarrays.server.Repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static io.getarrays.server.Enumeration.Status.SERVER_DOWN;
import static io.getarrays.server.Enumeration.Status.SERVER_UP;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepository serverRepository){
		return args -> {
		serverRepository.save(new Server(null,"192.168.1.160","Ubuntu Linux","20 GB","Personal PC","http://localhost:8080/server/image/server1.png",SERVER_UP));
		serverRepository.save(new Server(null,"192.168.1.58","Fedora Linux","20 GB","Dell Tower","http://localhost:8080/server/image/server2.png",SERVER_DOWN));
		serverRepository.save(new Server(null,"192.168.1.21","MS 2008","32 GB","Web Server","http://localhost:8080/server/image/server3.png",SERVER_DOWN));
		serverRepository.save(new Server(null,"192.168.1.14","Red Hat Enterprise Linux","120 GB","Mail Server","http://localhost:8080/server/image/server4.png",SERVER_UP));

		};
	}
}
