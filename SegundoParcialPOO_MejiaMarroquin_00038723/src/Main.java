import java.util.HashMap;
import java.util.Map;

abstract class BasePaymentRequestBuilder implements PaymentRequestBuilder {
    protected Map<String, String> params = new HashMap<>();

    public void setApiKey(String apiKey) {
        params.put("api_key", apiKey);
    }

    public void setUserName(String userName) {
        params.put("user_name", userName);
    }

    public abstract void setAdditionalToken();
}


class StripePaymentRequestBuilder extends BasePaymentRequestBuilder {
    public void setAdditionalToken() {
        String token = params.get("user_name") + ":" + "stripe";
        params.put("additional_token", token);
    }
}

class SquarePaymentRequestBuilder extends BasePaymentRequestBuilder {
    public void setAdditionalToken() {
        String token = params.get("user_name") + ":" + "square";
        params.put("additional_token", token);
    }
}

class AuthorizeNetPaymentRequestBuilder extends BasePaymentRequestBuilder {
    public void setAdditionalToken() {
        String token = params.get("user_name") + ":" + "authorize.net";
        params.put("additional_token", token);
    }
}

class AlipayPaymentRequestBuilder extends BasePaymentRequestBuilder {
    public void setAdditionalToken() {
        String token = params.get("user_name") + ":" + "alipay";
        params.put("additional_token", token);
    }
}

class N1COPaymentRequestBuilder extends BasePaymentRequestBuilder {
    public void setAdditionalToken() {
        String token = params.get("user_name") + ":" + "n1co";
        params.put("additional_token", token);
    }
}

class PaymentRequestDirector {
    private PaymentRequestBuilder builder;

    public PaymentRequestDirector(PaymentRequestBuilder builder) {
        this.builder = builder;
    }

    public void constructPaymentRequest(String apiKey, String userName) {
        builder.setApiKey(apiKey);
        builder.setUserName(userName);
        builder.setAdditionalToken();
    }

    public Map<String, String> getConstructedPaymentRequest() {
        return builder.params;
    }
}


public class Main {
    public static void main(String[] args) {
        PaymentRequestBuilder builder = new StripePaymentRequestBuilder();
        PaymentRequestDirector director = new PaymentRequestDirector(builder);

        
        director.constructPaymentRequest("stripe_api_key", "john_doe");
        
        Map<String, String> stripePaymentRequest = director.getConstructedPaymentRequest();
        System.out.println("Stripe Payment Request: " + stripePaymentRequest);
        
    }
}
