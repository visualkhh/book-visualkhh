<?xml version="1.0" encoding="euc-kr"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                      version="1.0">
	<xsl:output method="html" />

	<xsl:template match="/">
		<xsl:apply-templates select="productlist" />
	</xsl:template>
	
	<xsl:template match="productlist">
		<xsl:for-each select="product">
			<xsl:sort select="title" order="descending" />
			<p><xsl:value-of select="title" /></p>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>
<!-- //
스텝퍼
선풍기
볼트
// -->