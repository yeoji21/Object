package com.baek.methods;

public class FitnessExample {
    public String testableHtml(PageData pageData, boolean includeSuiteSetup) throws Exception{
        return new TestableHtmlBuilder(pageData, includeSuiteSetup).invoke();
    }
    
    private class TestableHtmlBuilder {
        private PageData pageData;
        private boolean includeSuiteSetup;
        private WikiPage wikiPage;
        private StringBuffer buffer;

        public TestableHtmlBuilder(PageData pageData, boolean includeSuiteSetup) {
            this.pageData = pageData;
            this.includeSuiteSetup = includeSuiteSetup;
            wikiPage = pageData.getWikiPage();
            buffer = new StringBuffer();
        }

        public String invoke() throws Exception {
            if (isTestPage()) {
                includeSetups();
                buffer.append(pageData.getContent());
                includeTeardowns();
            }
            pageData.setContent(buffer.toString());
            return pageData.getHtml();
        }

        private boolean isTestPage() {
            return pageData.hasAttribute("Test");
        }

        private void includeTeardowns() {
            includeInherited("TearDown", "teardown");
            if (includeSuiteSetup)
                includeInherited(SuiteResponder.SUITE_TEARDOWN_NAME, "teardown");
        }

        private void includeSetups() {
            if (includeSuiteSetup)
                includeInherited(SuiteResponder.SUITE_SETUP_NAME, "setup");
            includeInherited("SetUp", "setup");
        }

        private void includeInherited(Object suiteSetupName, String setup1) {
            WikiPage suiteSetup = PageCrawlerImpl.getInheritedPage(suiteSetupName, wikiPage);
            if (suiteSetup != null) {
                includePage(suiteSetup, setup1);
            }
        }

        private void includePage(WikiPage suiteSetup, String setup1) {
            WikiPagePath pagePath = wikiPage.getPageCrawler().getFullPath(suiteSetup);
            String pagePathName = PathParser.render(pagePath);
            buffer.append("!include -" + setup1 + " .").append(pagePathName).append("\n");
        }
    }
}