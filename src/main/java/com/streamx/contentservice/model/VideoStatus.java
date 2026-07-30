package com.streamx.contentservice.model;

/**
 * Tracks the video processing lifecycle.
 * Flow:
 * PENDING -> UPLOADED -> ENCODING -> ENCODED-> READY
 *                                 -> FAILED
 */
public enum VideoStatus {
    PENDING,
    UPLOADED,
    ENCODING,
    ENCODED,
    READY,
    FAILED
}