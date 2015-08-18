<?xml version="1.0" encoding="euc-kr"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                      version="1.0">
	<xsl:output method="html" />

	<xsl:template match="/">
		<xsl:apply-templates select="productlist" />
	</xsl:template>
	
	<xsl:template match="productlist">
		<xsl:apply-templates select="product" />
	</xsl:template>
	
	<xsl:template match="product">
		<p><xsl:value-of select="title" /></p>
	</xsl:template>
</xsl:stylesheet>
<!-- //
볼트
선풍기
스텝퍼
// -->