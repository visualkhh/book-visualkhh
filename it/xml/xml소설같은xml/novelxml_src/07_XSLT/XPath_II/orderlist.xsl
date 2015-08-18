<?xml version="1.0" encoding="euc-kr"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                      version="1.0">
	<xsl:output method="html" />

	<xsl:template match="/">
		<xsl:apply-templates select="order/productlist" />
	</xsl:template>
	
	<xsl:template match="order/productlist">
		<p><xsl:value-of select="name(..)" /></p>
		<p><xsl:value-of select="../sender/name" /></p>
		<p><xsl:value-of select="name(//receiver)" /></p>
		<p><xsl:value-of select="/order/receiver/name" /></p>
	</xsl:template>
</xsl:stylesheet>
<!-- //
order
이명진
receiver
최영관
// -->