package com.nashss.se.collectosstash.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.nashss.se.collectosstash.activity.request.GetAllComicBooksRequest;
import com.nashss.se.collectosstash.activity.results.GetAllComicBooksResults;

public class GetAllComicBooksLambda
        extends LambdaActivityRunner<GetAllComicBooksRequest, GetAllComicBooksResults
        implements RequestHandler<LambdaRequest<GetAllComicBooksRequest>,LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetAllComicBooksRequest> input, Context context) {
        return super.runActivity(() -> input.fromQuery(query ->
                GetAllComicBooksRequestRequest.builder()
                        .withId(query.get("id"))
                        .withDate(query.get("date"))
                        .build()), (request, serviceComponent) ->
                serviceComponent.provideGetAllComicBooksActivity().handleRequest(request)
        );
    }
}
