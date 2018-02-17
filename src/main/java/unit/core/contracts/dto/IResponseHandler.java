package unit.core.contracts.dto;

public interface IResponseHandler<TResponse> {

    TResponse handle();

}
