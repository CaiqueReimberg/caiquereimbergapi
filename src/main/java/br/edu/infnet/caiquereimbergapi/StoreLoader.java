package br.edu.infnet.caiquereimbergapi;

import br.edu.infnet.caiquereimbergapi.clients.ViaCepFeignClient;
import br.edu.infnet.caiquereimbergapi.model.domain.Store;
import br.edu.infnet.caiquereimbergapi.model.service.StoreService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Order(1)
@Component
public class StoreLoader implements ApplicationRunner {
    private final StoreService storeService;
    // private final ViaCepFeignClient cepFeignClient;

    public StoreLoader(StoreService storeService) {
        this.storeService = storeService;
        // this.cepFeignClient = cepFeignClient;
    }

    private final Map<Integer, Store> stores = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Running Store Loader");

        FileReader file = new FileReader("stores.csv");
        BufferedReader bufferedReader = new BufferedReader(file);
        String line = bufferedReader.readLine();
        String[] fields = null;

        while (line != null) {
            fields = line.split(";");

            // Livraria Sul;vendas@livrariasul.com.br;1198765432;04094000;11222333000155
            Store store = new Store();
            store.setName(fields[0]);
            store.setEmail(fields[1]);
            store.setPhone(fields[2]);
            // store.setAddress(cepFeignClient.findByCep(fields[3]));
            store.setCnpj(fields[4]);
            store.setActive(true);

            storeService.create(store);

            line = bufferedReader.readLine();
        }

        storeService.getAll().forEach(System.out::println);

        bufferedReader.close();
    }
}
