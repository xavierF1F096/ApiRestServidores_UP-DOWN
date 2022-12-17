package io.getarrays.server.Service;

import io.getarrays.server.Model.Server;
import io.getarrays.server.Repository.ServerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static io.getarrays.server.Enumeration.Status.SERVER_DOWN;
import static io.getarrays.server.Enumeration.Status.SERVER_UP;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ServerServiceImpl implements  ServerService{

    private final ServerRepository serverRepository;

    @Override
    public Server create(Server server) {
        log.info("Saving new server: {}",server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepository.save(server);
    }

    private String setServerImageUrl() {
        String [] imageNames={"server1.png","server2.png","server3.png","server4.png"};
        //asegurarte que estas ocupando .toUriString()
        return ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/server/image/"+imageNames[new Random().nextInt(4)]).toUriString();
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IP; {}",ipAddress);
        Server server = new Server();
        try {
            server=serverRepository.findByIpAddress(ipAddress);
            InetAddress address=InetAddress.getByName(ipAddress);
            server.setStatus(address.isReachable(1000) ? SERVER_UP : SERVER_DOWN);
            serverRepository.save(server);

        }catch (NullPointerException e){
         log.info("No exists ipAddress database..");
        }
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers.....");
        return serverRepository.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by id: {}",id);
        return serverRepository.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Update server: {}",server.getName());
        return serverRepository.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Delete server by ID: {}",id);
        serverRepository.deleteById(id);
        return Boolean.TRUE;
    }
}
