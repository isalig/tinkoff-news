package io.aiico.tnews.domain

/***
 * Unit test examples. Not full coverage.
 */
class NewsInteractorTest {

//    @Test
//    fun `returns news list`() {
//        // Arrange
//        val fakeNewsTitle = createFakeNewsTitle()
//        val apiSource = Single.just(
//            createFakeNewsTitlesResponse(
//                listOf(fakeNewsTitle)
//            )
//        )
//        val expectedNewsList = listOf(
//            News(
//                id = fakeNewsTitle.id,
//                title = fakeNewsTitle.text,
//                publicationDate = fakeNewsTitle.publicationDate.milliseconds
//            )
//        )
//
//        val mockedApi: NewsApi = mock { on(mock.getNewsTitles()).thenReturn(apiSource) }
//        val mockedDao: NewsDao = mock()
//        val sup = NewsInteractor(mockedApi, mockedDao)
//        val result: TestObserver<List<News>>
//
//        // Act
//        result = sup
//            .getNewsList(true)
//            .test()
//            .await()
//
//        // Assert
//        result.assertValue { value -> value == expectedNewsList }
//    }
//
//    @Test
//    fun `retrieves data only from api in force refresh case`() {
//        // Arrange
//        val apiSource = Single.just(
//            createFakeNewsTitlesResponse(
//                listOf(createFakeNewsTitle())
//            )
//        )
//        val mockedApi: NewsApi = mock {
//            on(mock.getNewsTitles()).thenReturn(apiSource)
//        }
//        val mockedDao: NewsDao = mock()
//        val sup = NewsInteractor(mockedApi, mockedDao)
//
//        // Act
//        sup
//            .getNewsList(true)
//            .test()
//            .await()
//
//        // Assert
//        verify(mockedApi, times(1)).getNewsTitles()
//        verify(mockedDao, never()).getAllNews()
//    }
//
//    private fun createFakeNewsTitlesResponse(news: List<NewsTitle>) = NewsResponse(
//        resultCode = "200",
//        payload = news,
//        trackingId = "0"
//    )
//
//    private fun createFakeNewsTitle() = NewsTitle(
//        "123",
//        "news-title",
//        "title text",
//        NewsDate(0),
//        0
//    )
}
