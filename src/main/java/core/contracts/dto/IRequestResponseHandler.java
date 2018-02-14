package core.contracts.dto;

public interface IRequestResponseHandler<TRequest, TResponse> {

    TResponse handle(TRequest message);

}
