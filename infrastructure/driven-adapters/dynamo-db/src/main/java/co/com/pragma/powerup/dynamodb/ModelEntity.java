package co.com.pragma.powerup.dynamodb;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

/* Enhanced DynamoDB annotations are incompatible with Lombok #1932
         https://github.com/aws/aws-sdk-java-v2/issues/1932*/
@DynamoDbBean
public class ModelEntity {

    private String metrica;
    private Double valor;

    public ModelEntity() {
    }

    public ModelEntity(String metrica, Double valor) {
        this.metrica = metrica;
        this.valor = valor;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("metrica")
    public String getMetrica() {
        return metrica;
    }

    public void setMetrica(String metrica) {
        this.metrica = metrica;
    }

    @DynamoDbAttribute("valor")
    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
