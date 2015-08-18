<?xml version="1.0"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                      version="1.0">
	<xsl:output method="html" />

	<xsl:template match="/">
		<xsl:apply-templates select="Catalog/Book" />
	</xsl:template>

	<xsl:template match="Catalog/Book">
		<xsl:choose>
			<xsl:when test="Title/@part='XML'">
				<font color="red">
					<p><xsl:value-of select="Title" /></p>
				</font>
			</xsl:when>
			<xsl:when test="Title/@part='XSLT'">
				<font color="blue">
					<p><xsl:value-of select="Title" /></p>
				</font>
			</xsl:when>
			<xsl:otherwise>
				<font color="black">
					<p><xsl:value-of select="Title" /></p>
				</font>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>
<!-- //
Professional VB6 XML
XSLT Programmer's Reference
C++ Programming
// -->