package com.nashss.se.collectorsstash.lambda;

import com.nashss.se.collectorsstash.activity.request.GetPriceComicBookRequest;
import com.nashss.se.collectorsstash.activity.results.GetPriceComicBookResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class GetPriceComicBookLambda
        extends LambdaActivityRunner<GetPriceComicBookRequest, GetPriceComicBookResult>
        implements RequestHandler<LambdaRequest<GetPriceComicBookRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetPriceComicBookRequest> input, Context context) {
        return super.runActivity(() -> input.fromPath(path ->
                GetPriceComicBookRequest.builder()
                        .withSeriesId(path.get("seriesId"))
                        .withPrice(Integer.parseInt(path.get("price")))
                        .build()), (request, serviceComponent) ->
                serviceComponent.provideGetPriceComicBookActivity().handleRequest(request)
        );
    }
}
