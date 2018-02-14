package core.contracts.dto;

public interface IResponseHandler<TResponse> {

    TResponse handle();

}
