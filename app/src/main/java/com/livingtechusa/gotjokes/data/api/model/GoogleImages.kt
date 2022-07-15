package com.livingtechusa.gotjokes.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.json.JSONObject



@JsonClass(generateAdapter = true)
data class GoogleImages(
    @Json(name = "context")
    val context: Context = Context(),
    @Json(name = "items")
    val items: List<Item> = listOf(),
    @Json(name = "kind")
    val kind: String = "", // customsearch#search
    @Json(name = "queries")
    val queries: Queries = Queries(),
    @Json(name = "searchInformation")
    val searchInformation: SearchInformation = SearchInformation(),
    @Json(name = "url")
    val url: Url = Url()
) {
    @JsonClass(generateAdapter = true)
    data class Context(
        @Json(name = "title")
        val title: String = "" // Google
    ) {
        companion object {
            @JvmStatic
            fun buildFromJson(jsonObject: JSONObject?): Context? {

                jsonObject?.run {
                    return Context(
                        optString("title")
                    )
                }
                return null
            }
        }
    }

    @JsonClass(generateAdapter = true)
    data class Item(
        @Json(name = "cacheId")
        val cacheId: String? = "", // u8TG4n0BCLIJ
        @Json(name = "displayLink")
        val displayLink: String = "", // www.rd.com
        @Json(name = "formattedUrl")
        val formattedUrl: String = "", // https://www.rd.com/list/funny-photos/
        @Json(name = "htmlFormattedUrl")
        val htmlFormattedUrl: String = "", // https://www.rd.com/list/<b>funny</b>-<b>photos</b>/
        @Json(name = "htmlSnippet")
        val htmlSnippet: String = "", // Jan 27, 2020 <b>...</b> The best <b>funny photos</b>. We all could use a good laugh every now and then. Check out this collection of <b>funny pictures</b> starting with this&nbsp;...
        @Json(name = "htmlTitle")
        val htmlTitle: String = "", // <b>Funny Photos</b> You Won&#39;t Be Able to Stop Laughing at | Reader&#39;s ...
        @Json(name = "kind")
        val kind: String = "", // customsearch#result
        @Json(name = "link")
        val link: String = "", // https://www.rd.com/list/funny-photos/
        @Json(name = "pagemap")
        val pagemap: Pagemap = Pagemap(),
        @Json(name = "snippet")
        val snippet: String = "", // Jan 27, 2020 ... The best funny photos. We all could use a good laugh every now and then. Check out this collection of funny pictures starting with this ...
        @Json(name = "title")
        val title: String = "" // Funny Photos You Won't Be Able to Stop Laughing at | Reader's ...
    ) {
        @JsonClass(generateAdapter = true)
        data class Pagemap(
            @Json(name = "blogposting")
            val blogposting: List<Blogposting>? = listOf(),
            @Json(name = "cse_image")
            val cseImage: List<CseImage> = listOf(),
            @Json(name = "cse_thumbnail")
            val cseThumbnail: List<CseThumbnail> = listOf(),
            @Json(name = "imageobject")
            val imageobject: List<Imageobject>? = listOf(),
            @Json(name = "listitem")
            val listitem: List<Listitem>? = listOf(),
            @Json(name = "metatags")
            val metatags: List<Metatag> = listOf(),
            @Json(name = "organization")
            val organization: List<Organization>? = listOf(),
            @Json(name = "person")
            val person: List<Person>? = listOf(),
            @Json(name = "webpage")
            val webpage: List<Webpage>? = listOf()
        ) {
            @JsonClass(generateAdapter = true)
            data class Blogposting(
                @Json(name = "datemodified")
                val datemodified: String = "", // 2022-07-14
                @Json(name = "datepublished")
                val datepublished: String = "", // 2022-07-14
                @Json(name = "headline")
                val headline: String = "" // Look ahead to 2022’s Green Gala & GSB Dinner as we celebrate 10 years of Charity!!
            ) {
                companion object {
                    @JvmStatic
                    fun buildFromJson(jsonObject: JSONObject?): Blogposting? {

                        jsonObject?.run {
                            return Blogposting(
                                optString("datemodified"),
                                optString("datepublished"),
                                optString("headline")
                            )
                        }
                        return null
                    }
                }
            }

            @JsonClass(generateAdapter = true)
            data class CseImage(
                @Json(name = "src")
                val src: String = "" // https://www.rd.com/wp-content/uploads/2022/01/GettyImages-1139039193-MLedit-1.jpg
            ) {
                companion object {
                    @JvmStatic
                    fun buildFromJson(jsonObject: JSONObject?): CseImage? {

                        jsonObject?.run {
                            return CseImage(
                                optString("src")
                            )
                        }
                        return null
                    }
                }
            }

            @JsonClass(generateAdapter = true)
            data class CseThumbnail(
                @Json(name = "height")
                val height: String = "", // 225
                @Json(name = "src")
                val src: String = "", // https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSpDzJGPMG-ohdubwWAdjvV53UcpjOwZ1GOMjXvCRhY2yLrIAccL94bjHaL
                @Json(name = "width")
                val width: String = "" // 225
            ) {
                companion object {
                    @JvmStatic
                    fun buildFromJson(jsonObject: JSONObject?): CseThumbnail? {

                        jsonObject?.run {
                            return CseThumbnail(
                                optString("height"),
                                optString("src"),
                                optString("width")
                            )
                        }
                        return null
                    }
                }
            }

            @JsonClass(generateAdapter = true)
            data class Imageobject(
                @Json(name = "contenturl")
                val contenturl: String? = "", // https://parade.com/.image/t_share/MTkwNTgxMTA1NjczMTE4ODQ1/funny-pictures-7-jpg.jpg
                @Json(name = "height")
                val height: String? = "", // 250
                @Json(name = "url")
                val url: String = "", // https://parade.com/.image/t_share/MTkwNTgxMTA1NjczMTE4ODQ1/funny-pictures-7-jpg.jpg
                @Json(name = "width")
                val width: String? = "" // 480
            ) {
                companion object {
                    @JvmStatic
                    fun buildFromJson(jsonObject: JSONObject?): Imageobject? {

                        jsonObject?.run {
                            return Imageobject(
                                optString("contenturl"),
                                optString("height"),
                                optString("url"),
                                optString("width")
                            )
                        }
                        return null
                    }
                }
            }

            @JsonClass(generateAdapter = true)
            data class Listitem(
                @Json(name = "position")
                val position: String = "" // 1
            ) {
                companion object {
                    @JvmStatic
                    fun buildFromJson(jsonObject: JSONObject?): Listitem? {

                        jsonObject?.run {
                            return Listitem(
                                optString("position")
                            )
                        }
                        return null
                    }
                }
            }

            @JsonClass(generateAdapter = true)
            data class Metatag(
                @Json(name = "apple-mobile-web-app-capable")
                val appleMobileWebAppCapable: String? = "", // no
                @Json(name = "apple-mobile-web-app-status-bar-style")
                val appleMobileWebAppStatusBarStyle: String? = "", // black
                @Json(name = "apple-mobile-web-app-title")
                val appleMobileWebAppTitle: String? = "", // Parade: Entertainment, Recipes, Health, Life, Holidays
                @Json(name = "application-name")
                val applicationName: String? = "", // Pexels
                @Json(name = "article:content_tier")
                val articleContentTier: String? = "", // free
                @Json(name = "article:modified_time")
                val articleModifiedTime: String? = "", // 2022-01-04T15:46:52+00:00
                @Json(name = "article:opinion")
                val articleOpinion: String? = "", // false
                @Json(name = "article:publisher")
                val articlePublisher: String? = "", // https://www.facebook.com/ReadersDigest
                @Json(name = "author")
                val author: String? = "", // Maryn Liles
                @Json(name = "companies")
                val companies: String? = "", // funny-pictures-7-jpg; funny pictures; funny pictures; funny pictures; funny photo; funny pics; funny pictures; funny pics; Laughing Horse; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny photo; funny pictures; funny pictures; funny pictures; funny photo; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures; Funny face; funny pictures; funny pictures; funny pictures; funny pictures; funny pictures
                @Json(name = "contentid")
                val contentid: String? = "", // 416815
                @Json(name = "cxenseparse:recs:bii-imgalt")
                val cxenseparseRecsBiiImgalt: String? = "", // tom hardy dog
                @Json(name = "date")
                val date: String? = "", // 2021-10-04
                @Json(name = "dcterms.datecopyrighted")
                val dctermsDatecopyrighted: String? = "", // 2022
                @Json(name = "facebook-domain-verification")
                val facebookDomainVerification: String? = "", // xsm5wtv6dad9pr0j3gk8h029u3xwek
                @Json(name = "fb:app_id")
                val fbAppId: String? = "", // 1460464994268018
                @Json(name = "fb:pages")
                val fbPages: String? = "", // 45598662525
                @Json(name = "format-detection")
                val formatDetection: String? = "", // telephone=no
                @Json(name = "handheldfriendly")
                val handheldfriendly: String? = "", // True
                @Json(name = "ia:markup_url")
                val iaMarkupUrl: String? = "", // https://www.insider.com/funny-animal-photos-perfect-timing-2019-3?fbia
                @Json(name = "insider")
                val insider: String? = "", // Snapchat
                @Json(name = "item-id")
                val itemId: String? = "", // ci02a51486300d247d
                @Json(name = "lastmod")
                val lastmod: String? = "", // 2021-10-04T13:59:55Z
                @Json(name = "linkedin:owner")
                val linkedinOwner: String? = "", // mid:1d5f7b
                @Json(name = "mobileoptimized")
                val mobileoptimized: String? = "", // 320
                @Json(name = "msapplication-config")
                val msapplicationConfig: String? = "", // /public/assets/INSIDER/US/favicons/browserconfig.xml?v=2021-08
                @Json(name = "msapplication-tilecolor")
                val msapplicationTilecolor: String? = "", // #05a081
                @Json(name = "msapplication-tileimage")
                val msapplicationTileimage: String? = "", // https://www.rd.com/wp-content/uploads/2018/10/NEWRDicon10.9.18.png?fit=270,264
                @Json(name = "msvalidate.01")
                val msvalidate01: String? = "", // C4A2A50A49418C4FE0D75E82953A553B
                @Json(name = "news_keywords")
                val newsKeywords: String? = "", // Features, Animals, Dogs, Comedy, Viral, Photography, Wildlife, Funny, Visual Slideshow, Animal photos, Gabbi Shaw
                @Json(name = "next-head-count")
                val nextHeadCount: String? = "", // 48
                @Json(name = "og:description")
                val ogDescription: String? = "", // If you're having a hard week (or, heck, are just plain bored), these funny photos are exactly what you need to make it through the day.
                @Json(name = "og:image")
                val ogImage: String? = "", // https://www.rd.com/wp-content/uploads/2019/09/GettyImages-621924830-scaled.jpg
                @Json(name = "og:image:height")
                val ogImageHeight: String? = "", // 1829
                @Json(name = "og:image:width")
                val ogImageWidth: String? = "", // 2560
                @Json(name = "og:locale")
                val ogLocale: String? = "", // en_US
                @Json(name = "og:site_name")
                val ogSiteName: String? = "", // Reader's Digest
                @Json(name = "og:title")
                val ogTitle: String? = "", // 50 Funny Pictures That Will Crack You Up
                @Json(name = "og:type")
                val ogType: String? = "", // article
                @Json(name = "og:updated_time")
                val ogUpdatedTime: String? = "", // 2021-08-27T11:20:21.000Z
                @Json(name = "og:url")
                val ogUrl: String? = "", // https://www.rd.com/list/funny-photos/
                @Json(name = "pagename")
                val pagename: String? = "", // 50 Funny Pictures That Will Crack You Up
                @Json(name = "pagetype")
                val pagetype: String? = "", // article
                @Json(name = "parsely-author")
                val parselyAuthor: String? = "", // Maryn Liles
                @Json(name = "parsely-image-url")
                val parselyImageUrl: String? = "", // https://parade.com/.image/c_fill%2Ccs_srgb%2Cfl_progressive%2Ch_400%2Cq_auto:good%2Cw_620/MTkwNTgxMTA1NjczMTE4ODQ1/funny-pictures-7-jpg.jpg
                @Json(name = "parsely-link")
                val parselyLink: String? = "", // https://parade.com/1190026/marynliles/funny-pictures/
                @Json(name = "parsely-post-id")
                val parselyPostId: String? = "", // ci02a51486300d247d
                @Json(name = "parsely-pub-date")
                val parselyPubDate: String? = "", // 2022-05-15T17:55:22Z
                @Json(name = "parsely-section")
                val parselySection: String? = "", // Entertainment
                @Json(name = "parsely-tags")
                val parselyTags: String? = "", // photos,funny,Entertainment
                @Json(name = "parsely-title")
                val parselyTitle: String? = "", // Get Ready to LOL! These 60 Hilariously Funny Pictures Are Guaranteed to Turn Any Frown Upside-Down - Parade: Entertainment, Recipes, Health, Life, Holidays
                @Json(name = "38 photos of animals that were taken at the perfect time")
                val photosOfAnimalsThatWereTakenAtThePerfectTime: String? = "", // The Fastest Way to Share a Moment!
                @Json(name = "phx:content-item-id")
                val phxContentItemId: String? = "", // ci02a51486300d247d
                @Json(name = "phx:content-object-type")
                val phxContentObjectType: String? = "", // ContentArticle
                @Json(name = "phx:exclusive-content-type")
                val phxExclusiveContentType: String? = "", // free
                @Json(name = "phx:site-keyword")
                val phxSiteKeyword: String? = "", // parade
                @Json(name = "pinterest")
                val pinterest: String? = "", // nohover
                @Json(name = "pinterest-rich-pin")
                val pinterestRichPin: String? = "", // true
                @Json(name = "pinterestapp:pinner")
                val pinterestappPinner: String? = "", // https://www.pinterest.com/taffyc816/
                @Json(name = "pinterestapp:pins")
                val pinterestappPins: String? = "", // 6780
                @Json(name = "pubdate")
                val pubdate: String? = "", // 2021-10-04T13:59:02Z
                @Json(name = "published")
                val published: String? = "", // 2022-05-15T17:55:22Z
                @Json(name = "referrer")
                val referrer: String? = "", // unsafe-url
                @Json(name = "sailthru.author")
                val sailthruAuthor: String? = "", // Maryn Liles
                @Json(name = "sailthru.date")
                val sailthruDate: String? = "", // 2022-05-15T17:55:22Z
                @Json(name = "sailthru.description")
                val sailthruDescription: String? = "", // These funny pictures, photos and images are guaranteed to make you laugh. Share these funny pictures with friends and family and make them smile.
                @Json(name = "sailthru.image.full")
                val sailthruImageFull: String? = "", // https://parade.com/.image/t_share/MTkwNTgxMTA1NjczMTE4ODQ1/funny-pictures-7-jpg.jpg
                @Json(name = "sailthru.image.thumb")
                val sailthruImageThumb: String? = "", // https://i.insider.com/6156666bb414c10018633fb1?width=160&format=jpeg
                @Json(name = "sailthru.tags")
                val sailthruTags: String? = "", // photos,funny,Entertainment
                @Json(name = "sailthru.title")
                val sailthruTitle: String? = "", // Get Ready to LOL! These 60 Hilariously Funny Pictures Are Guaranteed to Turn Any Frown Upside-Down
                @Json(name = "sailthru.verticals")
                val sailthruVerticals: String? = "", // lifestyle
                @Json(name = "section")
                val section: String? = "", // Entertainment
                @Json(name = "tags")
                val tags: String? = "", // photos, funny
                @Json(name = "tbi-vertical")
                val tbiVertical: String? = "", // Lifestyle
                @Json(name = "theme-color")
                val themeColor: String? = "", // #232a34
                @Json(name = "title")
                val title: String? = "", // 38 photos of animals that were taken at the perfect time
                @Json(name = "twitter:app:id:googleplay")
                val twitterAppIdGoogleplay: String? = "", // com.thechive
                @Json(name = "twitter:app:id:ipad")
                val twitterAppIdIpad: String? = "", // 448999087
                @Json(name = "twitter:app:id:iphone")
                val twitterAppIdIphone: String? = "", // 448999087
                @Json(name = "twitter:card")
                val twitterCard: String? = "", // summary_large_image
                @Json(name = "twitter:creator")
                val twitterCreator: String? = "", // @readersdigest
                @Json(name = "twitter:data1")
                val twitterData1: String? = "", // Marissa Laliberte
                @Json(name = "twitter:data2")
                val twitterData2: String? = "", // 5 minutes
                @Json(name = "twitter:description")
                val twitterDescription: String? = "", // After a dull and boring week, sometimes we need a little something to perk us up and laugh. Luckily, really funny pictures are a quick and easy cure! These
                @Json(name = "twitter:domain")
                val twitterDomain: String? = "", // parade.com
                @Json(name = "twitter:image")
                val twitterImage: String? = "", // https://parade.com/.image/t_share/MTkwNTgxMTA1NjczMTE4ODQ1/funny-pictures-7-jpg.jpg
                @Json(name = "twitter:image:src")
                val twitterImageSrc: String? = "", // https://i.pinimg.com/custom_covers/200x150/64880119570790617_1370575561.jpg
                @Json(name = "twitter:label1")
                val twitterLabel1: String? = "", // Written by
                @Json(name = "twitter:label2")
                val twitterLabel2: String? = "", // Est. reading time
                @Json(name = "twitter:site")
                val twitterSite: String? = "", // @readersdigest
                @Json(name = "twitter:title")
                val twitterTitle: String? = "", // 50 Funny Pictures That Will Crack You Up
                @Json(name = "twitter:url")
                val twitterUrl: String? = "", // https://parade.com/1190026/marynliles/funny-pictures/
                @Json(name = "viewport")
                val viewport: String = "" // width=device-width, initial-scale=1
            ) {
                companion object {
                    @JvmStatic
                    fun buildFromJson(jsonObject: JSONObject?): Metatag? {

                        jsonObject?.run {
                            return Metatag(
                                optString("apple-mobile-web-app-capable"),
                                optString("apple-mobile-web-app-status-bar-style"),
                                optString("apple-mobile-web-app-title"),
                                optString("application-name"),
                                optString("article:content_tier"),
                                optString("article:modified_time"),
                                optString("article:opinion"),
                                optString("article:publisher"),
                                optString("author"),
                                optString("companies"),
                                optString("contentid"),
                                optString("cxenseparse:recs:bii-imgalt"),
                                optString("date"),
                                optString("dcterms.datecopyrighted"),
                                optString("facebook-domain-verification"),
                                optString("fb:app_id"),
                                optString("fb:pages"),
                                optString("format-detection"),
                                optString("handheldfriendly"),
                                optString("ia:markup_url"),
                                optString("insider"),
                                optString("item-id"),
                                optString("lastmod"),
                                optString("linkedin:owner"),
                                optString("mobileoptimized"),
                                optString("msapplication-config"),
                                optString("msapplication-tilecolor"),
                                optString("msapplication-tileimage"),
                                optString("msvalidate.01"),
                                optString("news_keywords"),
                                optString("next-head-count"),
                                optString("og:description"),
                                optString("og:image"),
                                optString("og:image:height"),
                                optString("og:image:width"),
                                optString("og:locale"),
                                optString("og:site_name"),
                                optString("og:title"),
                                optString("og:type"),
                                optString("og:updated_time"),
                                optString("og:url"),
                                optString("pagename"),
                                optString("pagetype"),
                                optString("parsely-author"),
                                optString("parsely-image-url"),
                                optString("parsely-link"),
                                optString("parsely-post-id"),
                                optString("parsely-pub-date"),
                                optString("parsely-section"),
                                optString("parsely-tags"),
                                optString("parsely-title"),
                                optString("38 photos of animals that were taken at the perfect time"),
                                optString("phx:content-item-id"),
                                optString("phx:content-object-type"),
                                optString("phx:exclusive-content-type"),
                                optString("phx:site-keyword"),
                                optString("pinterest"),
                                optString("pinterest-rich-pin"),
                                optString("pinterestapp:pinner"),
                                optString("pinterestapp:pins"),
                                optString("pubdate"),
                                optString("published"),
                                optString("referrer"),
                                optString("sailthru.author"),
                                optString("sailthru.date"),
                                optString("sailthru.description"),
                                optString("sailthru.image.full"),
                                optString("sailthru.image.thumb"),
                                optString("sailthru.tags"),
                                optString("sailthru.title"),
                                optString("sailthru.verticals"),
                                optString("section"),
                                optString("tags"),
                                optString("tbi-vertical"),
                                optString("theme-color"),
                                optString("title"),
                                optString("twitter:app:id:googleplay"),
                                optString("twitter:app:id:ipad"),
                                optString("twitter:app:id:iphone"),
                                optString("twitter:card"),
                                optString("twitter:creator"),
                                optString("twitter:data1"),
                                optString("twitter:data2"),
                                optString("twitter:description"),
                                optString("twitter:domain"),
                                optString("twitter:image"),
                                optString("twitter:image:src"),
                                optString("twitter:label1"),
                                optString("twitter:label2"),
                                optString("twitter:site"),
                                optString("twitter:title"),
                                optString("twitter:url"),
                                optString("viewport")
                            )
                        }
                        return null
                    }
                }
            }

            @JsonClass(generateAdapter = true)
            data class Organization(
                @Json(name = "name")
                val name: String = "" // The Chive
            ) {
                companion object {
                    @JvmStatic
                    fun buildFromJson(jsonObject: JSONObject?): Organization? {

                        jsonObject?.run {
                            return Organization(
                                optString("name")
                            )
                        }
                        return null
                    }
                }
            }

            @JsonClass(generateAdapter = true)
            data class Person(
                @Json(name = "name")
                val name: String = "" // Stephen
            ) {
                companion object {
                    @JvmStatic
                    fun buildFromJson(jsonObject: JSONObject?): Person? {

                        jsonObject?.run {
                            return Person(
                                optString("name")
                            )
                        }
                        return null
                    }
                }
            }

            @JsonClass(generateAdapter = true)
            data class Webpage(
                @Json(name = "name")
                val name: String? = "", // Home
                @Json(name = "url")
                val url: String? = "" // https://thechive.com/
            ) {
                companion object {
                    @JvmStatic
                    fun buildFromJson(jsonObject: JSONObject?): Webpage? {

                        jsonObject?.run {
                            return Webpage(
                                optString("name"),
                                optString("url")
                            )
                        }
                        return null
                    }
                }
            }
            companion object {
                @JvmStatic
                fun buildFromJson(jsonObject: JSONObject?): Pagemap? {

                    jsonObject?.run {
                        return Pagemap(
                            ArrayList<Blogposting>().apply {
                                optJSONArray("blogposting")?.let {
                                    for(i in 0 until it.length()) {
                                        Blogposting.buildFromJson(it.getJSONObject(i))?.let { it1 -> this.add(it1) }
                                    }
                                }
                            },
                            ArrayList<CseImage>().apply {
                                optJSONArray("cse_image")?.let {
                                    for(i in 0 until it.length()) {
                                        CseImage.buildFromJson(it.getJSONObject(i))?.let { it1 -> this.add(it1) }
                                    }
                                }
                            },
                            ArrayList<CseThumbnail>().apply {
                                optJSONArray("cse_thumbnail")?.let {
                                    for(i in 0 until it.length()) {
                                        CseThumbnail.buildFromJson(it.getJSONObject(i))?.let { it1 -> this.add(it1) }
                                    }
                                }
                            },
                            ArrayList<Imageobject>().apply {
                                optJSONArray("imageobject")?.let {
                                    for(i in 0 until it.length()) {
                                        Imageobject.buildFromJson(it.getJSONObject(i))?.let { it1 -> this.add(it1) }
                                    }
                                }
                            },
                            ArrayList<Listitem>().apply {
                                optJSONArray("listitem")?.let {
                                    for(i in 0 until it.length()) {
                                        Listitem.buildFromJson(it.getJSONObject(i))?.let { it1 -> this.add(it1) }
                                    }
                                }
                            },
                            ArrayList<Metatag>().apply {
                                optJSONArray("metatags")?.let {
                                    for(i in 0 until it.length()) {
                                        Metatag.buildFromJson(it.getJSONObject(i))?.let { it1 -> this.add(it1) }
                                    }
                                }
                            },
                            ArrayList<Organization>().apply {
                                optJSONArray("organization")?.let {
                                    for(i in 0 until it.length()) {
                                        Organization.buildFromJson(it.getJSONObject(i))?.let { it1 -> this.add(it1) }
                                    }
                                }
                            },
                            ArrayList<Person>().apply {
                                optJSONArray("person")?.let {
                                    for(i in 0 until it.length()) {
                                        Person.buildFromJson(it.getJSONObject(i))?.let { it1 -> this.add(it1) }
                                    }
                                }
                            },
                            ArrayList<Webpage>().apply {
                                optJSONArray("webpage")?.let {
                                    for(i in 0 until it.length()) {
                                        Webpage.buildFromJson(it.getJSONObject(i))?.let { it1 -> this.add(it1) }
                                    }
                                }
                            }
                        )
                    }
                    return null
                }
            }
        }
        companion object {
            @JvmStatic
            fun buildFromJson(jsonObject: JSONObject?): Item? {

                jsonObject?.run {
                    return Pagemap.buildFromJson(optJSONObject("pagemap"))?.let {
                        Item(
                            optString("cacheId"),
                            optString("displayLink"),
                            optString("formattedUrl"),
                            optString("htmlFormattedUrl"),
                            optString("htmlSnippet"),
                            optString("htmlTitle"),
                            optString("kind"),
                            optString("link"),
                            it,
                            optString("snippet"),
                            optString("title")
                        )
                    }
                }
                return null
            }
        }
    }

    @JsonClass(generateAdapter = true)
    data class Queries(
        @Json(name = "nextPage")
        val nextPage: List<NextPage> = listOf(),
        @Json(name = "request")
        val request: List<Request> = listOf()
    ) {
        @JsonClass(generateAdapter = true)
        data class NextPage(
            @Json(name = "count")
            val count: Int = 0, // 10
            @Json(name = "cx")
            val cx: String = "", // 07f5aa9fbf617a226
            @Json(name = "inputEncoding")
            val inputEncoding: String = "", // utf8
            @Json(name = "outputEncoding")
            val outputEncoding: String = "", // utf8
            @Json(name = "safe")
            val safe: String = "", // off
            @Json(name = "searchTerms")
            val searchTerms: String = "", // funny pictures.
            @Json(name = "startIndex")
            val startIndex: Int = 0, // 11
            @Json(name = "title")
            val title: String = "", // Google Custom Search - funny pictures.
            @Json(name = "totalResults")
            val totalResults: String = "" // 3500000000
        ) {
            companion object {
                @JvmStatic
                fun buildFromJson(jsonObject: JSONObject?): NextPage? {

                    jsonObject?.run {
                        return NextPage(
                            optInt("count"),
                            optString("cx"),
                            optString("inputEncoding"),
                            optString("outputEncoding"),
                            optString("safe"),
                            optString("searchTerms"),
                            optInt("startIndex"),
                            optString("title"),
                            optString("totalResults")
                        )
                    }
                    return null
                }
            }
        }

        @JsonClass(generateAdapter = true)
        data class Request(
            @Json(name = "count")
            val count: Int = 0, // 10
            @Json(name = "cx")
            val cx: String = "", // 07f5aa9fbf617a226
            @Json(name = "inputEncoding")
            val inputEncoding: String = "", // utf8
            @Json(name = "outputEncoding")
            val outputEncoding: String = "", // utf8
            @Json(name = "safe")
            val safe: String = "", // off
            @Json(name = "searchTerms")
            val searchTerms: String = "", // funny pictures.
            @Json(name = "startIndex")
            val startIndex: Int = 0, // 1
            @Json(name = "title")
            val title: String = "", // Google Custom Search - funny pictures.
            @Json(name = "totalResults")
            val totalResults: String = "" // 3500000000
        ) {
            companion object {
                @JvmStatic
                fun buildFromJson(jsonObject: JSONObject?): Request? {

                    jsonObject?.run {
                        return Request(
                            optInt("count"),
                            optString("cx"),
                            optString("inputEncoding"),
                            optString("outputEncoding"),
                            optString("safe"),
                            optString("searchTerms"),
                            optInt("startIndex"),
                            optString("title"),
                            optString("totalResults")
                        )
                    }
                    return null
                }
            }
        }
        companion object {
            @JvmStatic
            fun buildFromJson(jsonObject: JSONObject?): Queries? {

                jsonObject?.run {
                    return Queries(
                        ArrayList<NextPage>().apply {
                            optJSONArray("nextPage")?.let {
                                for(i in 0 until it.length()) {
                                    NextPage.buildFromJson(it.getJSONObject(i))?.let { it1 -> this.add(it1) }
                                }
                            }
                        },
                        ArrayList<Request>().apply {
                            optJSONArray("request")?.let {
                                for(i in 0 until it.length()) {
                                    Request.buildFromJson(it.getJSONObject(i))?.let { it1 -> this.add(it1) }
                                }
                            }
                        }
                    )
                }
                return null
            }
        }
    }

    @JsonClass(generateAdapter = true)
    data class SearchInformation(
        @Json(name = "formattedSearchTime")
        val formattedSearchTime: String = "", // 0.21
        @Json(name = "formattedTotalResults")
        val formattedTotalResults: String = "", // 3,500,000,000
        @Json(name = "searchTime")
        val searchTime: Double = 0.0, // 0.213005
        @Json(name = "totalResults")
        val totalResults: String = "" // 3500000000
    ) {
        companion object {
            @JvmStatic
            fun buildFromJson(jsonObject: JSONObject?): SearchInformation? {

                jsonObject?.run {
                    return SearchInformation(
                        optString("formattedSearchTime"),
                        optString("formattedTotalResults"),
                        optDouble("searchTime"),
                        optString("totalResults")
                    )
                }
                return null
            }
        }
    }

    @JsonClass(generateAdapter = true)
    data class Url(
        @Json(name = "template")
        val template: String = "", // https://www.googleapis.com/customsearch/v1?q={searchTerms}&num={count?}&start={startIndex?}&lr={language?}&safe={safe?}&cx={cx?}&sort={sort?}&filter={filter?}&gl={gl?}&cr={cr?}&googlehost={googleHost?}&c2coff={disableCnTwTranslation?}&hq={hq?}&hl={hl?}&siteSearch={siteSearch?}&siteSearchFilter={siteSearchFilter?}&exactTerms={exactTerms?}&excludeTerms={excludeTerms?}&linkSite={linkSite?}&orTerms={orTerms?}&relatedSite={relatedSite?}&dateRestrict={dateRestrict?}&lowRange={lowRange?}&highRange={highRange?}&searchType={searchType}&fileType={fileType?}&rights={rights?}&imgSize={imgSize?}&imgType={imgType?}&imgColorType={imgColorType?}&imgDominantColor={imgDominantColor?}&alt=json
        @Json(name = "type")
        val type: String = "" // application/json
    ) {
        companion object {
            @JvmStatic
            fun buildFromJson(jsonObject: JSONObject?): Url? {

                jsonObject?.run {
                    return Url(
                        optString("template"),
                        optString("type")
                    )
                }
                return null
            }
        }
    }
    companion object {
        @JvmStatic
        fun buildFromJson(jsonObject: JSONObject?): GoogleImages? {

            jsonObject?.run {
                return Context.buildFromJson(optJSONObject("context"))?.let {
                    Queries.buildFromJson(optJSONObject("queries"))?.let { it1 ->
                        SearchInformation.buildFromJson(optJSONObject("searchInformation"))?.let { it2 ->
                            Url.buildFromJson(optJSONObject("url"))?.let { it3 ->
                                GoogleImages(
                                    it,
                                    ArrayList<Item>().apply {
                                        optJSONArray("items")?.let {
                                            for(i in 0 until it.length()) {
                                                Item.buildFromJson(it.getJSONObject(i))?.let { it1 -> this.add(it1) }
                                            }
                                        }
                                    },
                                    optString("kind"),
                                    it1,
                                    it2,
                                    it3
                                )
                            }
                        }
                    }
                }
            }
            return null
        }
    }
}