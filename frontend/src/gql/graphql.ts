/* eslint-disable */
import { TypedDocumentNode as DocumentNode } from '@graphql-typed-document-node/core';
export type Maybe<T> = T | null;
export type InputMaybe<T> = Maybe<T>;
export type Exact<T extends { [key: string]: unknown }> = { [K in keyof T]: T[K] };
export type MakeOptional<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]?: Maybe<T[SubKey]> };
export type MakeMaybe<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]: Maybe<T[SubKey]> };
export type MakeEmpty<T extends { [key: string]: unknown }, K extends keyof T> = { [_ in K]?: never };
export type Incremental<T> = T | { [P in keyof T]?: P extends ' $fragmentName' | '__typename' ? T[P] : never };
/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
  ID: { input: string; output: string; }
  String: { input: string; output: string; }
  Boolean: { input: boolean; output: boolean; }
  Int: { input: number; output: number; }
  Float: { input: number; output: number; }
  DateTime: { input: any; output: any; }
};

export type Attribute = BooleanAttribute | IntegerAttribute | LinkedElementAttribute | TextAttribute;

export type BaseProperty = {
  description: Scalars['String']['output'];
  id: Scalars['ID']['output'];
  name: Scalars['String']['output'];
};

export type BooleanAttribute = {
  __typename?: 'BooleanAttribute';
  property: BooleanProperty;
  value?: Maybe<BooleanValue>;
};

export type BooleanProperty = BaseProperty & {
  __typename?: 'BooleanProperty';
  description: Scalars['String']['output'];
  id: Scalars['ID']['output'];
  name: Scalars['String']['output'];
};

export type BooleanValue = {
  __typename?: 'BooleanValue';
  value: Scalars['Boolean']['output'];
};

export type Element = {
  __typename?: 'Element';
  attributes: Array<Attribute>;
  createdAt: Scalars['DateTime']['output'];
  id: Scalars['ID']['output'];
  type: Type;
};

export type IntegerAttribute = {
  __typename?: 'IntegerAttribute';
  property: IntegerProperty;
  value?: Maybe<IntegerValue>;
};

export type IntegerProperty = BaseProperty & {
  __typename?: 'IntegerProperty';
  description: Scalars['String']['output'];
  id: Scalars['ID']['output'];
  max?: Maybe<Scalars['Int']['output']>;
  min?: Maybe<Scalars['Int']['output']>;
  name: Scalars['String']['output'];
};

export type IntegerValue = {
  __typename?: 'IntegerValue';
  value: Scalars['Int']['output'];
};

export type LinkedElementAttribute = {
  __typename?: 'LinkedElementAttribute';
  property: LinkedElementProperty;
  value?: Maybe<LinkedElementValue>;
};

export type LinkedElementProperty = BaseProperty & {
  __typename?: 'LinkedElementProperty';
  description: Scalars['String']['output'];
  id: Scalars['ID']['output'];
  name: Scalars['String']['output'];
  typeRestrictions?: Maybe<Array<Type>>;
};

export type LinkedElementValue = {
  __typename?: 'LinkedElementValue';
  element?: Maybe<Element>;
  elementId: Scalars['ID']['output'];
  typeId: Scalars['ID']['output'];
};

export type Property = BooleanProperty | IntegerProperty | LinkedElementProperty | TextProperty;

export type Query = {
  __typename?: 'Query';
  elementById?: Maybe<Element>;
  elements: Array<Element>;
  properties: Array<Property>;
  propertyById?: Maybe<Property>;
  typeById?: Maybe<Type>;
  types: Array<Type>;
};


export type QueryElementByIdArgs = {
  id: Scalars['ID']['input'];
};


export type QueryElementsArgs = {
  typeId?: InputMaybe<Scalars['ID']['input']>;
};


export type QueryPropertyByIdArgs = {
  id: Scalars['ID']['input'];
};


export type QueryTypeByIdArgs = {
  id: Scalars['ID']['input'];
};

export type TextAttribute = {
  __typename?: 'TextAttribute';
  property: TextProperty;
  value?: Maybe<TextValue>;
};

export type TextProperty = BaseProperty & {
  __typename?: 'TextProperty';
  autoTrim: Scalars['Boolean']['output'];
  description: Scalars['String']['output'];
  id: Scalars['ID']['output'];
  maxLength?: Maybe<Scalars['Int']['output']>;
  name: Scalars['String']['output'];
};

export type TextValue = {
  __typename?: 'TextValue';
  value: Scalars['String']['output'];
};

export type Type = {
  __typename?: 'Type';
  description: Scalars['String']['output'];
  id: Scalars['ID']['output'];
  name: Scalars['String']['output'];
  properties: Array<Property>;
};

export type AllElementsQueryVariables = Exact<{ [key: string]: never; }>;


export type AllElementsQuery = { __typename?: 'Query', elements: Array<{ __typename: 'Element', id: string }> };


export const AllElementsDocument = {"kind":"Document","definitions":[{"kind":"OperationDefinition","operation":"query","name":{"kind":"Name","value":"allElements"},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"elements"},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"__typename"}},{"kind":"Field","name":{"kind":"Name","value":"id"}}]}}]}}]} as unknown as DocumentNode<AllElementsQuery, AllElementsQueryVariables>;