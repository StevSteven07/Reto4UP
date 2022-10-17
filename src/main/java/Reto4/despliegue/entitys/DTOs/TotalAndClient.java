
package Reto4.despliegue.entitys.DTOs;

import Reto4.despliegue.entitys.Client;


public class TotalAndClient {
    private Long total;
    private Client client;

    public TotalAndClient(Long total, Client client) {
        this.total= total;
        this.client= client;
    }  

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    
    
}
