package itbs.ghofrane.fatnassi.config;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitConfig {
	//  Nom de la file pour supprimer les PlanDistribution
    public static final String DELETE_PLAN_QUEUE = "delete.plan.queue";

    //  Déclaration de la file
    @Bean
    public Queue deletePlanQueue() {
        return new Queue(DELETE_PLAN_QUEUE, true); // durable = true
    }
}
