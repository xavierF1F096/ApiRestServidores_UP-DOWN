package io.getarrays.server.Repository;

import io.getarrays.server.Model.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepository extends JpaRepository<Server,Long> {
   Server findByIpAddress(String ipAddress);
}
