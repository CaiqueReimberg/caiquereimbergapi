package br.edu.infnet.caiquereimbergapi.clients;

import br.edu.infnet.caiquereimbergapi.model.domain.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "${api.viacep.url}")
public interface ViaCepFeignClient {

    @GetMapping("/{cep}/json/")
    Address findByCep(@PathVariable String cep);
}