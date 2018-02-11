package core.usecases.contracts.dto;

public interface IResponseHandler<TResponse> {

    TResponse handle();

}
