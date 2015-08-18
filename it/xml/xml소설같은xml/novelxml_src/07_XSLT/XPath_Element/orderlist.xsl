<?xml version="1.0" encoding="euc-kr"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="html" />

	<xsl:template match="/">
		<HTML>
			<HEAD>
				<TITLE>Book Catalog</TITLE>
			</HEAD>
			<BODY>
				<xsl:apply-templates select="orderlist/order/itemlist" />
			</BODY>
		</HTML>
	</xsl:template>
	
	<xsl:template match="orderlist/order/itemlist">
		<p><xsl:value-of select="name(.)" /></p>
		<p><xsl:value-of select="name(item[1])" /></p>
		<p><xsl:value-of select="item[3]/title" /></p>
		<p><xsl:value-of select="name(..)" /></p>
		<p><xsl:value-of select="../sender/name" /></p>
		<p><xsl:value-of select="name(/orderlist/order)" /></p>
		<p><xsl:value-of select="name(//sender)" /></p>
	</xsl:template>
</xsl:stylesheet>
<!-- //
itemlist
item
스텝퍼
order
김씨
order
sender
// -->