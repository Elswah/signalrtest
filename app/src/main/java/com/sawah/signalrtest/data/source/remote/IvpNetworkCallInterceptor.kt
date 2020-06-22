package com.sawah.signalrtest.data.source.remote

class IvpNetworkCallInterceptor {


    /* private lateinit var mockCallType: MockCallType

     private val contentType = "application/json"

     override fun intercept(chain: Interceptor.Chain): Response = getResponse(chain)
     // if(BuildConfig.isTestingEnabled) {
     //getMockedResponse(this)

     //  }else{


     // }

     private fun getResponse(chain: Interceptor.Chain): Response {

         val requestBuilder = chain.request().newBuilder()
         requestBuilder.addHeader("Accept-Language", "en")
         requestBuilder.addHeader("Accept", "application/json")
         requestBuilder.addHeader("Content-Type", "application/json")
         *//* if(accessToken!=null&&accessToken.trim().length()>0) {

             builder.addHeader("Authorization", "Bearer " + accessToken).build();
             builder.addHeader("x-Authorization",  accessToken).build();
         }*//*

        return chain.proceed(requestBuilder.build())

    }

    private fun getMockedResponse(chain: Interceptor.Chain): Response = when (mockCallType) {

        MockCallType.NEARBY -> getFeaturedOffer(chain)

        else -> emptyInterceptor(chain)
    }


    private fun getFeaturedOffer(chain: Interceptor.Chain): Response {

        val responseJson =
            "{\"Response\":{\"Body\":{\"Items\":[{\"Offers\":[{\"OfferDiscountTypeId\":\"1\",\"OfferSubCategoryId\":\"2\",\"NameEn\":\"Buffet Offer\",\"NameAr\":\"Buffet Offer\",\"DescriptionEn\":\"Write up test\",\"DescriptionAr\":\"Write up test\",\"ValidFrom\":\"2019-03-01 00:00:00.0\",\"ValidTo\":\"2019-04-30 00:00:00.0\",\"TermsAndConditions\":\"T & C\",\"IsFeatured\":\"0\",\"OfferId\":\"141\",\"CreationDate\":\"2019-03-31 12:54:58.077\",\"Name\":\"Buffet Offer\",\"Description\":\"Write up test\",\"DiscountType\":\"Buy One Get One Free\",\"OfferCategory\":\"Dining\",\"OfferSubCategory\":\"American\",\"ReSubmissionReason\":null,\"OfferAmount\":\"45\",\"OfferGroupId\":\"2\",\"OfferGroupName\":\"Limited Time Offers\",\"OfferGroupNameEn\":\"Limited Time Offers\",\"OfferGroupNameAr\":\"Limited Time Offers\",\"OfferGroupValidTo\":\"2019-04-19 12:00:00.0\",\"Branch\":{\"CompanyBranchId\":\"97\",\"CompanyName\":\"Company 1\",\"City\":\"Al Nahda\",\"Lan\":\"25.2392967\",\"Long\":\"55.3164092\",\"About\":\"sample about\",\"Phone1\":\"0507655473\",\"ImageURL\":[\"http://10.30.1.119:4555/gateway/Company/1.0/CompanyImage?MerchantID=170&BranchID=97&ImageID=1.jpeg\",\"http://10.30.1.119:4555/gateway/Company/1.0/CompanyImage?MerchantID=170&BranchID=97&ImageID=2.jpeg\"],\"LogoURL\":\"http://10.30.1.119:4555/gateway/Company/1.0/CompanyImage?MerchantID=170&Type=Logo\"}},{\"OfferDiscountTypeId\":\"1\",\"OfferSubCategoryId\":\"1\",\"NameEn\":\"Company 2 Offer\",\"NameAr\":\"يستعرض\",\"DescriptionEn\":\"Company 2 Offer\",\"DescriptionAr\":\"Company 2 Offer\",\"ValidFrom\":\"2019-01-01 12:00:00.0\",\"ValidTo\":\"2019-04-26 12:00:00.0\",\"TermsAndConditions\":\"Company 2 Offer\",\"IsFeatured\":\"1\",\"OfferId\":\"143\",\"CreationDate\":\"2019-04-02 17:47:13.467\",\"Name\":\"Company 2 Offer\",\"Description\":\"Company 2 Offer\",\"DiscountType\":\"Buy One Get One Free\",\"OfferCategory\":\"Dining\",\"OfferSubCategory\":\"Arabic\",\"ReSubmissionReason\":null,\"OfferAmount\":\"15\",\"OfferGroupId\":\"2\",\"OfferGroupName\":\"Limited Time Offers\",\"OfferGroupNameEn\":\"Limited Time Offers\",\"OfferGroupNameAr\":\"Limited Time Offers\",\"OfferGroupValidTo\":\"2019-04-10 00:00:00.0\",\"Branch\":{\"CompanyBranchId\":\"99\",\"CompanyName\":\"Company 2\",\"City\":\" ابو ظبي\",\"Lan\":\"24.9977314\",\"Long\":\"55.1744089\",\"About\":\"sample about\",\"Phone1\":\"0545522552\",\"ImageURL\":[\"http://10.30.1.119:4555/gateway/Company/1.0/CompanyImage?MerchantID=172&BranchID=99&ImageID=1.jpeg\",\"http://10.30.1.119:4555/gateway/Company/1.0/CompanyImage?MerchantID=172&BranchID=99&ImageID=2.jpeg\"],\"LogoURL\":\"http://10.30.1.119:4555/gateway/Company/1.0/CompanyImage?MerchantID=172&Type=Logo\"}},{\"OfferDiscountTypeId\":\"1\",\"OfferSubCategoryId\":\"1\",\"NameEn\":\"Company 1 Another Offer\",\"NameAr\":\"Company 1 Another Offer\",\"DescriptionEn\":\"Company 1 Another Offer\",\"DescriptionAr\":\"Company 1 Another Offer\",\"ValidFrom\":\"2019-04-02 00:00:00.0\",\"ValidTo\":\"2019-04-19 00:00:00.0\",\"TermsAndConditions\":\"Company 1 Another Offer\",\"IsFeatured\":\"1\",\"OfferId\":\"145\",\"CreationDate\":\"2019-04-03 16:08:53.73\",\"Name\":\"Company 1 Another Offer\",\"Description\":\"Company 1 Another Offer\",\"DiscountType\":\"Buy One Get One Free\",\"OfferCategory\":\"Dining\",\"OfferSubCategory\":\"Arabic\",\"ReSubmissionReason\":null,\"OfferAmount\":\"125\",\"OfferGroupId\":\"2\",\"OfferGroupName\":\"Limited Time Offers\",\"OfferGroupNameEn\":\"Limited Time Offers\",\"OfferGroupNameAr\":\"Limited Time Offers\",\"OfferGroupValidTo\":\"2019-04-19 12:00:00.0\",\"Branch\":{\"CompanyBranchId\":\"97\",\"CompanyName\":\"Company 1\",\"City\":\"Al Nahda\",\"Lan\":\"25.2392967\",\"Long\":\"55.3164092\",\"About\":\"sample about\",\"Phone1\":\"0507655473\",\"ImageURL\":[\"http://10.30.1.119:4555/gateway/Company/1.0/CompanyImage?MerchantID=170&BranchID=97&ImageID=1.jpeg\",\"http://10.30.1.119:4555/gateway/Company/1.0/CompanyImage?MerchantID=170&BranchID=97&ImageID=2.jpeg\"],\"LogoURL\":\"http://10.30.1.119:4555/gateway/Company/1.0/CompanyImage?MerchantID=170&Type=Logo\"}},{\"OfferDiscountTypeId\":\"1\",\"OfferSubCategoryId\":\"1\",\"NameEn\":\"Golden Offer V1 Terms 2\",\"NameAr\":\"Golden Offer V1 Terms 2\",\"DescriptionEn\":\"Golden Offer V1 Terms 2\",\"DescriptionAr\":\"Golden Offer V1 Terms 2\",\"ValidFrom\":\"2019-04-05 00:00:00.0\",\"ValidTo\":\"2019-04-10 00:00:00.0\",\"TermsAndConditions\":\"Golden Offer V1 Terms 2\",\"IsFeatured\":\"1\",\"OfferId\":\"150\",\"CreationDate\":\"2019-04-07 10:48:15.603\",\"Name\":\"Golden Offer V1 Terms 2\",\"Description\":\"Golden Offer V1 Terms 2\",\"DiscountType\":\"Buy One Get One Free\",\"OfferCategory\":\"Dining\",\"OfferSubCategory\":\"Arabic\",\"ReSubmissionReason\":null,\"OfferAmount\":\"80\",\"OfferGroupId\":\"2\",\"OfferGroupName\":\"Limited Time Offers\",\"OfferGroupNameEn\":\"Limited Time Offers\",\"OfferGroupNameAr\":\"Limited Time Offers\",\"OfferGroupValidTo\":\"2019-04-10 11:19:56.0\",\"Branch\":{\"CompanyBranchId\":\"95\",\"CompanyName\":\"chompa burgers\",\"City\":\"دبي\",\"Lan\":\"25.2042057\",\"Long\":\"55.2627207\",\"About\":\"sample about\",\"Phone1\":\"0569874521\",\"ImageURL\":[\"http://10.30.1.119:4555/gateway/Company/1.0/CompanyImage?MerchantID=168&BranchID=95&ImageID=1.jpeg\",\"http://10.30.1.119:4555/gateway/Company/1.0/CompanyImage?MerchantID=168&BranchID=95&ImageID=2.jpeg\",\"http://10.30.1.119:4555/gateway/Company/1.0/CompanyImage?MerchantID=168&BranchID=95&ImageID=3.jpeg\"],\"LogoURL\":\"http://10.30.1.119:4555/gateway/Company/1.0/CompanyImage?MerchantID=168&Type=Logo\"}},{\"OfferDiscountTypeId\":\"2\",\"OfferSubCategoryId\":\"3\",\"NameEn\":\"Golden Offer V2 Terms\",\"NameAr\":\"Golden Offer V2 Terms\",\"DescriptionEn\":\"Golden Offer V2 Terms Terms\",\"DescriptionAr\":\"Golden Offer V2 Terms Terms\",\"ValidFrom\":\"2019-04-03 00:00:00.0\",\"ValidTo\":\"2019-04-12 00:00:00.0\",\"TermsAndConditions\":\"Golden Offer V2 Terms Terms\",\"IsFeatured\":\"1\",\"OfferId\":\"151\",\"CreationDate\":\"2019-04-07 11:00:48.543\",\"Name\":\"Golden Offer V2 Terms\",\"Description\":\"Golden Offer V2 Terms Terms\",\"DiscountType\":\"Percentage Discount\",\"OfferCategory\":\"Insurance\",\"OfferSubCategory\":\"Ice Cream\",\"ReSubmissionReason\":null,\"OfferAmount\":\"90\",\"OfferGroupId\":\"2\",\"OfferGroupName\":\"Limited Time Offers\",\"OfferGroupNameEn\":\"Limited Time Offers\",\"OfferGroupNameAr\":\"Limited Time Offers\",\"OfferGroupValidTo\":\"2019-04-17 12:00:00.0\",\"Branch\":{\"CompanyBranchId\":\"95\",\"CompanyName\":\"chompa burgers\",\"City\":\"دبي\",\"Lan\":\"25.2042057\",\"Long\":\"55.2627207\",\"About\":\"sample about\",\"Phone1\":\"0569874521\",\"ImageURL\":[\"http://10.30.1.119:4555/gateway/Company/1.0/CompanyImage?MerchantID=168&BranchID=95&ImageID=1.jpeg\",\"http://10.30.1.119:4555/gateway/Company/1.0/CompanyImage?MerchantID=168&BranchID=95&ImageID=2.jpeg\",\"http://10.30.1.119:4555/gateway/Company/1.0/CompanyImage?MerchantID=168&BranchID=95&ImageID=3.jpeg\"],\"LogoURL\":\"http://10.30.1.119:4555/gateway/Company/1.0/CompanyImage?MerchantID=168&Type=Logo\"}}]},{\"Offers\":[{\"OfferDiscountTypeId\":\"1\",\"OfferSubCategoryId\":\"1\",\"NameEn\":\"Company 1 Another Offer\",\"NameAr\":\"Company 1 Another Offer\",\"DescriptionEn\":\"Company 1 Another Offer\",\"DescriptionAr\":\"Company 1 Another Offer\",\"ValidFrom\":\"2019-04-02 00:00:00.0\",\"ValidTo\":\"2019-04-19 00:00:00.0\",\"TermsAndConditions\":\"Company 1 Another Offer\",\"IsFeatured\":\"1\",\"OfferId\":\"145\",\"CreationDate\":\"2019-04-03 16:08:53.73\",\"Name\":\"Company 1 Another Offer\",\"Description\":\"Company 1 Another Offer\",\"DiscountType\":\"Buy One Get One Free\",\"OfferCategory\":\"Dining\",\"OfferSubCategory\":\"Arabic\",\"ReSubmissionReason\":null,\"OfferAmount\":\"125\",\"OfferGroupId\":\"3\",\"OfferGroupName\":\"Top Offers\",\"OfferGroupNameEn\":\"Top Offers\",\"OfferGroupNameAr\":\"أفضل العروض\",\"OfferGroupValidTo\":\"2019-04-10 11:19:56.0\",\"Branch\":{\"CompanyBranchId\":\"97\",\"CompanyName\":\"Company 1\",\"City\":\"Al Nahda\",\"Lan\":\"25.2392967\",\"Long\":\"55.3164092\",\"About\":\"sample about\",\"Phone1\":\"0507655473\",\"ImageURL\":[\"http://10.30.1.119:4555/gateway/Company/1.0/CompanyImage?MerchantID=170&BranchID=97&ImageID=1.jpeg\",\"http://10.30.1.119:4555/gateway/Company/1.0/CompanyImage?MerchantID=170&BranchID=97&ImageID=2.jpeg\"],\"LogoURL\":\"http://10.30.1.119:4555/gateway/Company/1.0/CompanyImage?MerchantID=170&Type=Logo\"}},{\"OfferDiscountTypeId\":\"3\",\"OfferSubCategoryId\":\"5\",\"NameEn\":\"Fixed 30\",\"NameAr\":\"Fixed 30\",\"DescriptionEn\":\"Fixed 30\",\"DescriptionAr\":\"Fixed 30\",\"ValidFrom\":\"2019-04-02 00:00:00.0\",\"ValidTo\":\"2019-04-25 00:00:00.0\",\"TermsAndConditions\":\"Fixed 30\",\"IsFeatured\":\"0\",\"OfferId\":\"158\",\"CreationDate\":\"2019-04-08 08:56:09.873\",\"Name\":\"Fixed 30\",\"Description\":\"Fixed 30\",\"DiscountType\":\"Fixed Discount\",\"OfferCategory\":\"Dining\",\"OfferSubCategory\":\"Pizza\",\"ReSubmissionReason\":null,\"OfferAmount\":\"30\",\"OfferGroupId\":\"3\",\"OfferGroupName\":\"Top Offers\",\"OfferGroupNameEn\":\"Top Offers\",\"OfferGroupNameAr\":\"أفضل العروض\",\"OfferGroupValidTo\":\"2019-04-10 11:19:56.0\",\"Branch\":{\"CompanyBranchId\":\"97\",\"CompanyName\":\"Company 1\",\"City\":\"Al Nahda\",\"Lan\":\"25.2392967\",\"Long\":\"55.3164092\",\"About\":\"sample about\",\"Phone1\":\"0507655473\",\"ImageURL\":[\"http://10.30.1.119:4555/gateway/Company/1.0/CompanyImage?MerchantID=170&BranchID=97&ImageID=1.jpeg\",\"http://10.30.1.119:4555/gateway/Company/1.0/CompanyImage?MerchantID=170&BranchID=97&ImageID=2.jpeg\"],\"LogoURL\":\"http://10.30.1.119:4555/gateway/Company/1.0/CompanyImage?MerchantID=170&Type=Logo\"}},{\"OfferDiscountTypeId\":\"2\",\"OfferSubCategoryId\":\"1\",\"NameEn\":\"NeedForSpeed\",\"NameAr\":\"NeedForSpeed\",\"DescriptionEn\":\"zxzx\",\"DescriptionAr\":\"zxzxzxz\",\"ValidFrom\":\"2019-04-09 00:00:00.0\",\"ValidTo\":\"2019-04-19 00:00:00.0\",\"TermsAndConditions\":\"zxzx\",\"IsFeatured\":\"0\",\"OfferId\":\"161\",\"CreationDate\":\"2019-04-09 15:11:48.217\",\"Name\":\"NeedForSpeed\",\"Description\":\"zxzx\",\"DiscountType\":\"Percentage Discount\",\"OfferCategory\":\"Dining\",\"OfferSubCategory\":\"Arabic\",\"ReSubmissionReason\":null,\"OfferAmount\":\"25\",\"OfferGroupId\":\"3\",\"OfferGroupName\":\"Top Offers\",\"OfferGroupNameEn\":\"Top Offers\",\"OfferGroupNameAr\":\"أفضل العروض\",\"OfferGroupValidTo\":\"2019-04-10 12:00:00.0\",\"Branch\":{\"CompanyBranchId\":\"104\",\"CompanyName\":\"NeedForSpeed\",\"City\":\"دبي\",\"Lan\":\"25.2047315\",\"Long\":\"55.2630537\",\"About\":\"sample about\",\"Phone1\":\"0500000000\",\"ImageURL\":[\"http://10.30.1.119:4555/gateway/Company/1.0/CompanyImage?MerchantID=176&BranchID=104&ImageID=1.jpeg\"],\"LogoURL\":\"http://10.30.1.119:4555/gateway/Company/1.0/CompanyImage?MerchantID=176&Type=Logo\"}}]}],\"Code\":\"200\"}}};\n"

        return createResponse(chain, responseJson, 200)
    }

    private fun emptyInterceptor(chain: Interceptor.Chain): Response {

        val responseString = "{" +
                "    \"error\": {" +
                "        \"code\": \"400004\"," +
                "        \"message\": \"Please set Interceptor using MockInterceptor getInstance().setMockCallType() Method.\"" +
                "    }" +
                "}"
        return createResponse(chain, responseString, 400)

    }

    private fun createResponse(
        chain: Interceptor.Chain,
        jsonResponse: String,
        code: Int
    ): Response {

        return with(chain) {

            Response.Builder().code(code).message(jsonResponse).request(this.request())
                .protocol(Protocol.HTTP_1_0).body(
                    jsonResponse.toByteArray().toResponseBody(contentType.toMediaTypeOrNull())
                ).addHeader("content-type", contentType).build()


        }
    }*/


}