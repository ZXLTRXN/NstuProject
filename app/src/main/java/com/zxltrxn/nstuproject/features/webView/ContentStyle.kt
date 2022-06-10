package com.zxltrxn.nstuproject.features.webView

data class ContentStyle(
    val removeOddBlocks: Boolean = true,
    val removeShadowedBorder: Boolean = true,
    val stretchMinContent: Boolean = false,
    val stretchTable: Boolean = false,
    val removeOddBlocksCiu: Boolean = false
){
    fun applyStyleInJS(): String {
        return buildString {
            append(beginJSFunction)
            if (removeOddBlocks) append(oddBlocks)
            if (removeOddBlocksCiu) append(oddBlocksCiu)

            append(beginStyle)
            if (removeShadowedBorder) append(shadowedBorder)
            if (stretchMinContent) append(stretchMinContentCss)
            if (stretchTable) append(stretchTableCss)
            append(endStyle)

            append(endJSFunction)
        }
    }

    companion object {
        const val beginJSFunction = "javascript:(function() {"
        const val endJSFunction = "})()"

        const val beginStyle = """var node = document.createElement('style');
            node.type = 'text/css';
            node.innerHTML = '"""
        const val endStyle = """';
            document.head.appendChild(node);"""

        const val oddBlocks: String = """
            document.getElementsByClassName('header-mobile')[0].style.display="none";
            document.getElementsByClassName('breadcrumbs')[0].style.display="none";
            document.getElementsByClassName('page-footer')[0].style.display="none";
            document.getElementsByClassName('page-bottom')[0].style.display="none";
            """

        const val oddBlocksCiu: String = """
            document.getElementsByClassName('navbar')[0].style.display="none";
            document.getElementsByClassName('page-header')[0].style="none";
            document.getElementsByClassName('footer')[0].style.display="none";
            """

        const val shadowedBorder = """
            .page-wrapper {
                box-shadow: none;
                -webkit-box-shadow: none;
            }
            """

        const val stretchMinContentCss = """
            .page-wrapper {
                min-width: min-content;
            }
            """

        const val stretchTableCss ="""
            .page-wrapper {
            width: 140%;
            }
            """
    }
}

